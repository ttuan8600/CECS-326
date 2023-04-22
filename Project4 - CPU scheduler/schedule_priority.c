/*
 * Priority scheduling
 */
#include <stdlib.h>
#include <stdio.h>
#include <stddef.h>

#include "task.h"
#include "list.h"
#include "cpu.h"
#include "schedulers.h"

struct node *head = NULL;
struct node *curr = NULL;
// struct node *final = NULL;

void add(char *name, int priority, int burst)
{
    // Create a new node and task
    struct node *new = malloc(sizeof(struct node));
    new->task = malloc(sizeof(struct task));
    new->task->name = name;
    new->task->burst = burst;
    new->task->priority = priority;
    new->next = NULL;

    // if head is null, set the new node as head and current
    if (head == NULL)
    {
        head = curr = new;
        return;
    }

    // if the new task has higher priority than the head, set it as the new head
    if (new->task->priority > head->task->priority)
    {
        new->next = head;
        head = new;
        return;
    }

    // iterate through the list to find the correct position for the new task
    struct node *prev = NULL;
    struct node *node = head;
    while (node != NULL && new->task->priority <= node->task->priority)
    {
        prev = node;
        node = node->next;
    }

    // insert the new node into the list
    if (prev != NULL)
    {
        prev->next = new;
        new->next = node;
    }
    else
    {
        head->next = new;
        curr = new;
    }
}

void schedule()
{
    curr = head;
    while (curr != NULL)
    {
        run(curr->task, curr->task->burst);
        curr = curr->next;
    }
}