/*
 * Priority scheduling
 */
#include <stdlib.h>
#include <stdio.h>
#include <stddef.h>

#include "task.h"
#include "list.h"
#include "cpu.h"

struct node *head = NULL;
struct node *current = NULL;
struct node *new = NULL;
struct node *final = NULL;

void add(char *name, int priority, int burst){
    if (head == NULL){
        head = malloc(sizeof(struct node));
        // set the name of the task
        head->task = malloc(sizeof(struct task));
        head->task->name = name;
        head->task->burst = burst;
        head->task->priority = priority;
        // set the next node to be null
        head->next = NULL;
        current = head;
    }else{
        new = malloc(sizeof(struct node));
        new->task = malloc(sizeof(struct task));
        new->task->burst = burst;
        new->task->name = name;
        new->task->priority = priority;
        // if current->next is NULL
        if (!(current->next)){
            if (((new->task->priority) < (current->task->priority)) || ((new->task->priority) == (current->task->priority))){
                current->next = new; // head points to second node
                new->next = NULL;
            }
            // if the second node burst is smaller than the current burst
            else{
                // set new to point to head which is in the second position
                new->next = current;
                // head now holds the address of new which is in the first position
                head = new;
            }
        }
        // if current->next is not NULL
        else{
            // if the new node burst is smaller than the current burst
            if (((new->task->priority) < (current->task->priority)) || ((new->task->priority) == (current->task->priority))){
                // set new to point to head which is in the second position
                new->next = current;
                // head now holds the address of new which is in the first position
                head = new;
            }
            // if the new node burst is larger than the current burst
            else{
                // set new to point to head which is in the second position
                new->next = current->next;
                // head now holds the address of new which is in the first position
                current->next = new;
            }
        }
        current = new;
    }
}

void schedule(){
    current = head;
    while (current != NULL){
        run(current->task, current->task->burst);
        current = current->next;
    }
}