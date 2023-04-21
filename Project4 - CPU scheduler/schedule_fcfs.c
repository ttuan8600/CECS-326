/**
 * FCFS scheduling
 */
 
#include <stdlib.h>
#include <stdio.h>

#include "task.h"
#include "list.h"
#include "cpu.h"
#include "schedulers.h"

struct node *head = NULL;
struct node *tail = NULL;
struct node *new = NULL;
int num = 0;

void add (char *name, int prior, int burst){
    if (head == NULL){
        head = malloc(sizeof(struct node));
        tail = malloc(sizeof(struct node));

        // set the name of the task
        head->task = malloc(sizeof(struct task));
        head->task->name = name;
        head->task->priority = prior;
        head->task->burst = burst;

        // set the next node to be null
        head->next = NULL;

        // set the tail to be the head
        tail = head;
    }else{
        // create a new node
        new = malloc(sizeof(struct node));

        tail->next = new;

        // set the name of the task
        new->task = malloc(sizeof(struct task));
        new->task->name = name;
        new->task->priority = prior;
        new->task->burst = burst;

        // set the next node to be null
        new->next = NULL;

        // set the tail to be the new node
        tail = new;
    }
}

void schedule(){
    
}

