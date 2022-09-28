#include<iostream>
#include<vector>
using namespace std;

// Some of the code used is from the example code from lecture 13

struct Node
{
	int value;
	Node* next;

	Node(int n) {
		value = n;
		next = nullptr;
	}

	Node(int n, Node* p) {
		value = n;
		next = p;
	}
};

class LinkedList {
private:
	Node* head;
	int size = 0;

	Node* get_node(int index) {
		if (index < 0 or index >= size) {
			throw range_error("IndexError: Index out of range");
		}

		Node* current = head;
		for (int i = 0; i < index; i++) {
			current = current->next;
		}
		return current;
	}

public:
	LinkedList() {
		head = nullptr;
	}

	LinkedList(vector<int> initial) {
		head = nullptr;

		for (int e : initial) {
			append(e);
			size++;
		}
	}

	~LinkedList() {
		Node* current;
		Node* next;

		current = head;

		while (current != nullptr) {
			next = current->next;
			delete current;
			current = next;
		}
	}

	void append(int val) {
		if (head == nullptr) {
			head = new Node(val);
			size++;
			return;
		}

		// Iterate to end of list
		Node* current;
		current = head;
		while (current->next != nullptr) {
			current = current->next;
		}

		// Link new node to end of list
		current->next = new Node(val);
		size++;
	}

	void print() {
		Node* current = head;
		cout << "[";
		while (current->next != nullptr) {
			cout << current->value;
			cout << ", ";
			current = current->next;
		}
		cout << current->value << "]" << endl;
	}

	int& operator[](int index) {
		return get_node(index)->value;
	}

	void insert(int val, int index) {
		if (index < 0 or index >= size) {
			throw range_error("IndexError: Index out of range");
		}
		Node* prev = get_node(index - 1);
		Node* next = prev->next;
		prev->next = new Node(val, next);
		size++;
	}

	void remove(int index) {
		if (index < 0 or index >= size) {
			throw range_error("IndexError: Index out of range");
		}
		Node* temp1 = head;
		if (index == 0) {
			head = temp1->next;
			delete[] temp1;
		}
		else {
			int n;
			for (n = 0; n < index - 2; n++) {
				temp1 = temp1->next;
			}
			Node* temp2 = temp1->next;
			temp1->next = temp2->next;
			delete[] temp2;
		}
		size--;
	}

	int pop(int index) {
		if (index < 0 or index >= size) {
			throw range_error("IndexError: Index out of range");
		}
		int value = operator[](index);
		Node* temp1 = head;
		if (index == 0) {
			head = temp1->next;
			delete[] temp1;
		}
		else {
			int n;
			for (n = 0; n < index - 1; n++) {
				temp1 = temp1->next;
			}
			Node* temp2 = temp1->next;
			temp1->next = temp2->next;
			delete[] temp2;
		}
		size--;
		return value;
	}

	int pop() {
		pop(size);
		size--;
	}
};

int main() {
	LinkedList exmpl({ 1, 2, 3, 4 });
	cout << "Making a list with given starting nodes:\n";
	exmpl.print();
	exmpl.append(34);
	cout << "Adding given value to the end:\n";
	exmpl.print();
	exmpl.remove(2);
	cout << "Removing value at given index:\n";
	exmpl.print();
	exmpl.insert(212, 2);
	cout << "Inserting given value at given index:\n";
	exmpl.print();
	exmpl.pop(4);
	cout << "Removing last element by index:\n";
	exmpl.print();

	cin.get();
	return 0;
}