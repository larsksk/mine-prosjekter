Part 3: Comparing the ArrayList and the LinkedList


Get element  i  by index:
	ArrayList	- O(1)		Goes directly to the indexed number.
	LinkedList	- O(i)		Iterantes through every number before the index.

Insert at front:
	ArrayList	- O(n)		Must move all elements n ahead to insert at front.
	LinkedList	- O(1)		Inserts via header.

Insert at back (aka append):
	ArrayList	- O(1)		Adds element at end of list.
	LinkedList	- O(n)		Must iterate through all nodes.

Insert into middle of list:
	ArrayList	- O(n-i)	Must move every elemt after the index one step ahead to insert value.
	LinkedList	- O(i)		Must iterate thorugh every index before getting to the right point of the insertion (i+1 or i+2 might be more correct here).

Remove element from front:
	ArrayList	- O(n)		Must move every element back one step.
	LinkedList	- O(1)		Remove via header.

Remove element from back:
	ArrayList	- O(1)		Just removes element from back, no movement of other values needed.
	LinkedList	- O(n)		Must iterate through whole list before removing.

Remove element from middle:
	ArrayList	- O(n-i)	Almost the same as insertion, here we need to move all elements after the index back.
	LinkedList	- O(i)		Here we must iterate though all the nodes before the index to get to the index, and then remove it.

Print:
	ArrayList	- O(n)		Iterates through all elements in list and prints them out as a list.
	LinkedList	- O(n)		Iterates thorugh all nodes and prints them out as a list.




Part 4: Josephus problem

n = 68
k = 7
Last surivor is 68!