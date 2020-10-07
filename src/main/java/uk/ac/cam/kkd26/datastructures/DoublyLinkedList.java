/*
 * Copyright (c) 2020, Krzysztof Druciarek
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package uk.ac.cam.kkd26.datastructures;

public class DoublyLinkedList<T> implements Stack<T>, List<T> {
	protected static class DLLI<T> {
		T payload;
		DLLI<T> next = null;
		DLLI<T> previous = null;

		private DLLI(T a) {
			payload = a;
		}

		@Override
		public String toString() {
			if (next == null) return payload + "";
			return payload + " " + next;
		}
	}

	private DLLI<T> head = null;
	private DLLI<T> tail = null;
	private long size = 0L;

	public DoublyLinkedList() {

	}

	public DoublyLinkedList(DLLI<T> d) {
		head = d;
	}

	public void addFront(T a) {
		DLLI<T> n = new DLLI<>(a);
		n.next = head;
		if (head != null) head.previous = n;
		else tail = n;
		head = n;
	}

	public void addBack(T a) {
		DLLI<T> n = new DLLI<>(a);
		n.previous = tail;
		if (tail != null) tail.next = n;
		else head = n;
		tail = n;
	}

	public T removeFront() throws IsEmpty {
		if (head == null) throw new IsEmpty();

		T h = head.payload;
		head = head.next;
		if (head != null) head.previous = null;
		else tail = null;

		return h;
	}

	public T removeBack() throws IsEmpty {
		if (tail == null) throw new IsEmpty();

		T h = tail.payload;
		tail = tail.previous;
		if (tail != null) tail.next = null;
		else head = null;

		return h;
	}

	public T getFront() throws IsEmpty {
		if (isEmpty()) throw new IsEmpty();
		return head.payload;
	}

	public DLLI<T> getHead() {
		return head;
	}

	public boolean isEmpty() {
		return getHead() == null;
	}

	@Override
	public T head() throws IsEmpty {
		return getFront();
	}

	@Override
	public void prepend(T x) {
		addFront(x);
	}

	@Override
	public List<T> tail() {
		return new DoublyLinkedList<>(getHead().next);
	}

	@Override
	public void setTail(List<T> tail) throws IsEmpty {
		if (tail == null) {
			head.next = null;
			return;
		}
		head.next = ((DoublyLinkedList<T>) tail).head;
	}

	public void push(T x) {
		addFront(x);
	}

	public T pop() throws IsEmpty {
		return removeFront();
	}

	public T top() throws IsEmpty {
		return getFront();
	}

	@Override
	public String toString() {
		if (head == null) return "";
		return head + "";
	}

	public static void main(String[] args) throws IsEmpty {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.addFront(1);
		list.addFront(2);
		list.addFront(3);
		list.addFront(4);
		list.addFront(5);
		list.addFront(6);
		System.out.println(list);
		Stack<Integer> s = new DoublyLinkedList<>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		System.out.println(s);
		s.pop();
		System.out.println(s);
		System.out.println(s.top());
		System.out.println();

		List<Integer> l = new DoublyLinkedList<>();
		l.prepend(1);
		l.prepend(2);
		l.prepend(3);
		l.prepend(4);
		l = l.tail();
		System.out.println(l);
	}
}
