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
struct node *curr = NULL;
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
        curr = head;
    }else{
        new = malloc(sizeof(struct node));
        new->task = malloc(sizeof(struct task));
        new->task->name = name;
        new->task->burst = burst;
        new->task->priority = priority;
        // if curr->next is NULL
        if (!(curr->next)){
            if (((new->task->priority) < (curr->task->priority)) || ((new->task->priority) == (curr->task->priority))){
                // head points to second node 
                curr->next = new; 
                new->next = NULL;
            }
            // if the second node burst is smaller than the curr burst
            else{
                // set new to point to head which is in the second position
                new->next = curr;
                // head now holds the address of new which is in the first position
                head = new;
            }
        }
        // if curr->next is not NULL
        else{
            // if the new node burst is smaller than the curr burst
            if (((new->task->priority) < (curr->task->priority)) || ((new->task->priority) == (curr->task->priority))){
                // set new to point to head which is in the second position
                new->next = curr;
                // head now holds the address of new which is in the first position
                head = new;
            }
            // if the new node burst is larger than the curr burst
            else{
                // set new to point to head which is in the second position
                new->next = curr->next;
                // head now holds the address of new which is in the first position
                curr->next = new;
            }
        }
        curr = new;
    }
}

void schedule(){
    curr = head;
    while (curr != NULL){
        run(curr->task, curr->task->burst);
        curr = curr->next;
    }
}