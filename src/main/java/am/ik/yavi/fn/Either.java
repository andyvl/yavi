/*
 * Copyright (C) 2018 Toshiaki Maki <makingx@gmail.com>
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
package am.ik.yavi.fn;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public final class Either<L, R> {
	private final L left;
	private final R right;

	private Either(L left, R right) {
		if (left == null && right == null) {
			throw new IllegalArgumentException("Both left and right are null!");
		}
		this.left = left;
		this.right = right;
	}

	public static <L, R> Either<L, R> left(L left) {
		return new Either<>(left, null);
	}

	public static <L, R> Either<L, R> right(R right) {
		return new Either<>(null, right);
	}

	public Optional<L> left() {
		return Optional.ofNullable(this.left);
	}

	public Optional<R> right() {
		return Optional.ofNullable(this.right);
	}

	public L leftOrElseGet(Function<R, L> rightToLeft) {
		return this.left().orElseGet(() -> rightToLeft.apply(this.right));
	}

	public R rightOrElseGet(Function<L, R> leftToRight) {
		return this.right().orElseGet(() -> leftToRight.apply(this.left));
	}

	public boolean isLeft() {
		return this.left != null;
	}

	public boolean isRight() {
		return this.right != null;
	}

	public Either<R, L> swap() {
		return new Either<>(this.right, this.left);
	}

	public <U> U fold(Function<L, U> leftMapper, Function<R, U> rightMapper) {
		if (isLeft()) {
			return leftMapper.apply(this.left);
		}
		return rightMapper.apply(this.right);
	}

	public <X, Y> Either<X, Y> bimap(Function<L, X> leftMapper,
			Function<R, Y> rightMapper) {
		if (isLeft()) {
			return new Either<>(leftMapper.apply(this.left), null);
		}
		return new Either<>(null, rightMapper.apply(this.right));
	}

	public <X> Either<X, R> leftMap(Function<L, X> leftMapper) {
		if (isLeft()) {
			return new Either<>(leftMapper.apply(this.left), null);
		}
		return new Either<>(null, this.right);
	}

	public <Y> Either<L, Y> rightMap(Function<R, Y> rightMapper) {
		if (isRight()) {
			return new Either<>(null, rightMapper.apply(this.right));
		}
		return new Either<>(this.left, null);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Either<?, ?> either = (Either<?, ?>) o;
		return Objects.equals(left, either.left) && Objects.equals(right, either.right);
	}

	@Override
	public int hashCode() {
		return Objects.hash(left, right);
	}
}
