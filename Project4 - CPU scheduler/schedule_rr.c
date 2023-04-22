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

void add(char *name, int priority, int burst)
{
    if (head == NULL)
    {
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
    }
    else
    {
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
void schedule()
{
    struct node *curr = head;
    struct node *ref = head;
    struct node *monitor = NULL;
    monitor = malloc(sizeof(struct node));
    monitor = head;
    int newburst = 0;
    int totburst = 0;
    int check = 1;

    while (ref != NULL)
    {
        if (ref->task->bust >= 10)
        {
            newburst = ref->task->burst - 10;
            if (curr != head)
            {
                while (monitor != curr)
                {
                    if (curr->task > name == monitor->task->name)
                    {
                        check = 0;
                        break;
                    }
                    monitor = monitor->next;
                }
                monitor = head;
            }
            totburst = totburst + 10;
            ref->task->burst = 10;
            run(curr->task, 10);
        }
        else if (ref->task->burst < 10)
        {
            newburst = 0;
            totburst = totburst + ref->task->burst;
            run(curr->task, ref->task->burst);
        }
        while (1)
        {
            if (curr->next != NULL)
            {
                curr = curr->next;
                if (curr->next == NULL)
                {
                    if (newburst != 0)
                    {
                        struct node *newnode = malloc(sizeof(struct node));
                        newnode->task = malloc(sizeof(struct task));
                        newnode->task->name = curr->task->name;
                        newnode->task->burst = newburst;
                        newnode->task->priority = curr->task->priority;
                        ref = ref->next;
                        curr->next = newnode;
                        newnode->next = NULL;
                        curr = ref;
                        break;
                    }
                    else if (newburst == 0)
                    {
                        ref = ref->next;
                        curr = ref;
                        break;
                    }
                }
            }
            else
            {
                if (newburst != 0)
                {
                    struct node *new = malloc(sizeof(struct node));
                    new->task = malloc(sizeof(struct task));
                    new->task->name = ref->task->name;
                    new->task->burst = ref->task->priority;
                    new->task->priority = newburst;
                    ref->next = new;
                    ref = ref->next;
                    new->next = NULL;
                    curr = new;
                }
                else
                {
                    ref = ref->next;
                }
                break;
            }
        }
    }
}