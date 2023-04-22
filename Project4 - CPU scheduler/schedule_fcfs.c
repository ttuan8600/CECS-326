/**
 * FCFS scheduling
 */

#include <stdlib.h>
#include <stdio.h>

#include "task.h"
#include "list.h"
#include "cpu.h"

struct node *head = NULL;
struct node *tail = NULL;

void add(char *name, int prior, int burst)
{
    struct node *new = malloc(sizeof(struct node));

    // set the name of the task
    new->task = malloc(sizeof(struct task));
    new->task->name = name;
    new->task->priority = prior;
    new->task->burst = burst;

    // set the next node to be null
    new->next = NULL;

    if (head == NULL)
    {
        // set the head and tail to be the new node
        head = new;
        tail = new;
    }
    else
    {
        // set the tail's next node to be the new node
        tail->next = new;

        // set the tail to be the new node
        tail = new;
    }
}

void schedule()
{
    struct node *ref = head;
    while (ref != NULL)
    {
        run(ref->task, ref->task->burst);
        ref = ref->next;
    }
}
