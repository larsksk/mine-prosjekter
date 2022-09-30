// Some of the code used is from the example code from lecture 13

#include<iostream>
#include<vector>
using namespace std;

class ArrayList {
private:
	int *data;
	int capacity;

	void resize() {
		capacity *= 2;
		int *tmp = new int[capacity];
		for (int i = 0; i < size; i++) {
			tmp[i] = data[i];
		}
		delete[] data;
		data = tmp;
	}

public:
	int size;

	ArrayList() {
		size = 0;
		capacity = 1;
		data = new int[capacity];
	}

	ArrayList(vector<int> initial) {
		size = 0;
		capacity = 1;
		data = new int[capacity];

		for (int e : initial) {
			append(e);
			size++;
		}
	}

	~ArrayList() {
		delete[] data;
	}

	int length() {
		return size;
	}

	int cap() {
		return capacity;
	}

	void append(int n) {
		if (size >= capacity) {
			resize();
		}
		data[size] = n;
		size++;
	}

	int insert(int val, int index) {
		if (index >= 0 && index <= size - 1) {
			for (int i = size; i > index; i--) {
				data[i] = data[i - 1];
			}
			data[index] = val;
			size++;
		}
		else {
			throw range_error("Non-existent position");
		}
	}

	void remove(int index) {
		if (index >= 0 && index <= size - 1) {
			for (int i = size; i > index; i++) {
				data[i] = data[i + 1];
			}
			size--;
			if (0.25*capacity > size) {
				shrink_to_fit();
			}
		}
		else {
			throw range_error("Non-existent position");
		}
	}

	int pop(int index) {
		if (index >= 0 && index <= size - 1) {
			int value = data[index];
			for (int i = size; i > index; i++) {
				data[i] = data[i + 1];
			}
			size--;
			if (0.25*capacity > size) {
				shrink_to_fit();
			}
			return value;
		}
		else {
			throw range_error("Non-existent position");
		}
	}

	int pop() {
		if (size > 0) {
			data[-size] = 0;
			size--;
			if (0.25*capacity >= size) {
				shrink_to_fit();
			}
		}
		else {
			throw range_error("Empty array");
		}
	}

	void shrink_to_fit() {
		while (capacity / 2 >= size) {
			capacity /= 2;
		}
	}

	int& operator[] (int i) {
		if (0 <= i && i < size) {
			return data[i];
		}
		else {
			throw out_of_range("IndexError");
		}
	}

	void print() {
		cout << "[";
		for (int i = 0; i < size - 1; i++) {
			cout << data[i];
			cout << ", ";
		}
		cout << data[size - 1] << "]" << endl;
	}
};

bool is_prime(int value) {
	int i;
	for (i = 2; i <= value / 2; ++i) {
		if (value % i == 0) {
			return false;
		}
		else {
			return true;
		}
	}
}

int main() {
	ArrayList exmpl;
	int i, count = 0;
	for (i = 2; i > 0; ++i) {
		if (is_prime(i) == true) {
			exmpl.append(i);
			count++;
			if (count == 10) {
				break;
			}
		}
	}
	cout << "The first 10 primes are: \n";
	exmpl.print();

	cout << "\n\nNow we will test the automatic shrinking:\n";
	cout << "Size:      ";
	cout << exmpl.length();
	cout << "\nCapacity:  ";
	cout << exmpl.cap();
	cout << "\n";
	exmpl.pop();
	exmpl.print();
	exmpl.pop();
	exmpl.print();
	exmpl.pop();
	exmpl.print();
	exmpl.pop();
	exmpl.print();
	exmpl.pop();
	cout << "\nSize:      ";
	cout << exmpl.length();
	cout << "\nCapacity:  ";
	cout << exmpl.cap();
	cout << "\n";
	exmpl.print();
	exmpl.pop();
	cout << "\nSize:      ";
	cout << exmpl.length();
	cout << "\nCapacity:  ";
	cout << exmpl.cap();
	cout << "\n";
	exmpl.print();

	cout << "\nWe can see that when the size reaches 0.25 times the capacity,\nthe automatic shrink to fit function is called";


	cin.get();
	return 0;
}