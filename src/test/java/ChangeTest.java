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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.ac.cam.kkd26.dynamic.Change;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static uk.ac.cam.kkd26.dynamic.Change.give;
import static uk.ac.cam.kkd26.dynamic.Change.setMONEY;

@RunWith(JUnit4.class)
public class ChangeTest {

	@Test
	public void change_0() {
		// ARRANGE
		int initial = 0;
		System.out.println("\np: " + initial);

		// ACT
		ArrayList<Integer> a = give(initial);

		// ASSERT
		int[] correct = new int[]{};
		System.out.println("Correct: " + Arrays.toString(correct));
		System.out.println("Output: " + a);
		assertThat(a.size()).isEqualTo(correct.length);
		for (int i = 0; i < a.size(); i++) {
			assertThat(a.get(i).compareTo(correct[i]));
		}
	}

	@Test
	public void change_10() {
		// ARRANGE
		int initial = 10;
		System.out.println("\np: " + initial);

		// ACT
		ArrayList<Integer> a = give(initial);

		// ASSERT
		int[] correct = new int[]{5, 5};
		System.out.println("Correct: " + Arrays.toString(correct));
		System.out.println("Output: " + a);
		assertThat(a.size()).isEqualTo(correct.length);
		for (int i = 0; i < a.size(); i++) {
			assertThat(a.get(i).compareTo(correct[i]));
		}
	}

	@Test
	public void change_341() {
		// ARRANGE
		int initial = 341;
		System.out.println("\np: " + initial);

		// ACT
		ArrayList<Integer> a = give(initial);

		// ASSERT
		int[] correct = new int[]{200, 100, 25, 5, 5, 5, 1};
		System.out.println("Correct: " + Arrays.toString(correct));
		System.out.println("Output: " + a);
		assertThat(a.size()).isEqualTo(correct.length);
		for (int i = 0; i < a.size(); i++) {
			assertThat(a.get(i).compareTo(correct[i]));
		}
	}

	@Test
	public void change_8_different_money() {
		// ARRANGE
		int initial = 8;
		Change c = new Change();
		setMONEY(new ArrayList<>(List.of(1, 3, 5, 6)));
		System.out.println("\np: " + initial);

		// ACT
		ArrayList<Integer> a = give(initial);

		// ASSERT
		int[] correct = new int[]{5, 3};
		System.out.println("Correct: " + Arrays.toString(correct));
		System.out.println("Output: " + a);
		assertThat(a.size()).isEqualTo(correct.length);
		for (int i = 0; i < a.size(); i++) {
			assertThat(a.get(i).compareTo(correct[i]));
		}
	}
}


