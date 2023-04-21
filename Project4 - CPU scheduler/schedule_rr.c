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
int num = 0;

void add(char *name, int priority, int burst){
    num+=1
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
    struct node *current = head;
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
        if (current->task->burst > 10){
            run(current->task, 10);
            newburst = current->task->burst - 10;
            current->task->burst = newburst;
            totburst -= 10;
            current = current->next;
        }else{
            run(current->task, current->task->burst);
            totburst -= current->task->burst;
            current->task->burst = 0;
            current = current->next;
        }
    }
}