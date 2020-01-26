/*
 * Copyright (C) 2018-2020 Toshiaki Maki <makingx@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package am.ik.yavi.arguments;

/**
 * @since 0.3.0
 */
public class Arguments1<A1> {

	protected final A1 arg1;

	Arguments1(A1 arg1) {
		this.arg1 = arg1;
	}

	public final A1 arg1() {
		return this.arg1;
	}

	public final <X> X map(Arguments1.Mapper<A1, X> mapper) {
		return mapper.map(arg1);
	}

	public interface Mapper<A1, X> {
		X map(A1 arg1);
	}
}