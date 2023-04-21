/**
 * Round-robin scheduling
 */

#include <stdlib.h>
#include <stdio.h>
#include <stddef.h>

#include "task.h"
#include "list.h"
#include "cpu.h"

struct node *head = NULL;
struct node *last = NULL;
struct node *new = NULL;
struct node *test = NULL;

void add(char *name, int priority, int burst){
    if (head == NULL){
        head = malloc(sizeof(struct node));
        last = malloc(sizeof(struct node));
        // set the name of the task
        head->task = malloc(sizeof(struct task));
        head->task->name = name;
        head->task->burst = burst;
        head->task->priority = priority;
        // set the next node to be null
        head->next = NULL;
        last = head;
    }else{
        new = malloc(sizeof(struct node));
        last->next = new;
        new->task = malloc(sizeof(struct task));
        new->task->name = name;
        new->task->burst = burst;
        new->task->priority = priority;
        new->next = NULL;
        last = new;
    }
}

// invoke the scheduler
void schedule(){
    struct node *curr = head;
    struct node *ref = head;
    struct node *monitor = NULL;
    monitor = malloc(sizeof(struct node));
    monitor = head;
    int newburst = 0;
    int totburst = 0;
    while (monitor != NULL){
        totburst += monitor->task->burst;
        monitor = monitor->next;
    }
    while (totburst > 0){
        if (curr->task->burst > 10){
            run(curr->task, 10);
            newburst = curr->task->burst - 10;
            curr->task->burst = newburst;
            totburst -= 10;
            curr = curr->next;
        }else{
            run(curr->task, curr->task->burst);
            totburst -= curr->task->burst;
            curr->task->burst = 0;
            curr = curr->next;
        }
    }
}