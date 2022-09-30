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

class CircLinkedList {
private:
	Node* head;
	int size = 0;

	Node* get_node(int index) {
		int number = index;
		// Making it so that the node returned is the right one, even though the index is over the size, so it loops
		while (number > size) {
			number - size;
		}
		Node* current = head;
		Node* root = current;
		for (int i = 0; i < number; i++) {
			current = current->next;
		}
		return current;
	}

public:
	CircLinkedList() {
		head = nullptr;
	}

	CircLinkedList(vector<int> initial) {
		head = nullptr;

		for (int e : initial) {
			append(e);
			size++;
		}
	}

	~CircLinkedList() {
		Node* current;
		Node* next;
		Node* root;

		current = head;
		root = head;

		while (current != root) {
			next = current->next;
			delete current;
			current = next;
		}
	}

	void append(int val) {
		if (head == nullptr) {
			head = new Node(val);
			head->next = head;
			size++;
			return;
		}

		// Iterate to end of list
		Node* current;
		Node* root;
		current = head;
		root = current;
		while (current->next != root) {
			current = current->next;
		}

		// Link new node to start of list
		// There is something not working here. After 1 node is added, more nodes cannot be added
		// The rest of the code should work, but I have not managed to fix this problen. I am missing something
		current->next = new Node(val);
		// Connecting new node to root (first node)
		current->next = root;
		size++;
		return;
	}

	void print() {
		Node* current = head;
		cout << "[";
		while (current->next != head) {
			cout << current->value;
			cout << ", ";
			current = current->next;
		}
		cout << current->value << "]" << endl;
	}

	int& operator[](int index) {
		return get_node(index)->value;
	}

	vector<int> josephus_sequence(int k) {
		// Iterating thorugh and deleting every k node, until 1 node remains
		Node* temp1 = head;
		while (size > 1) {
			if (k == 0) {
				head = temp1->next;
				delete[] temp1;
			}
			else {
				int n;
				for (n = 0; n < k - 2; n++) {
					temp1 = temp1->next;
				}
				Node* temp2 = temp1->next;
				temp1->next = temp2->next;
				delete[] temp2;
			}
			size--;
		}
	}
};

int last_man_standing(int n, int k) {
	CircLinkedList exmpl({});
	int i;
	for (i = 0; i <= n - 1; i++) {
		exmpl.append(i);
	}
	//exmpl.josephus_sequence(k);
	cout << "The last man standing is:\n";
	exmpl.print();
	return 0;
}

int main() {
	last_man_standing(10, 3);
	cin.get();
	return 0;
}