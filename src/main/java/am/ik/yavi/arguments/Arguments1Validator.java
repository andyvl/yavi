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

import java.util.List;
import java.util.Locale;

import am.ik.yavi.core.CollectionValidator;
import am.ik.yavi.core.ConstraintCondition;
import am.ik.yavi.core.ConstraintGroup;
import am.ik.yavi.core.ConstraintPredicates;
import am.ik.yavi.core.ConstraintViolations;
import am.ik.yavi.core.ConstraintViolationsException;
import am.ik.yavi.core.Validator;
import am.ik.yavi.core.ValidatorSubset;
import am.ik.yavi.fn.Either;
import am.ik.yavi.fn.Pair;
import am.ik.yavi.message.MessageFormatter;

/**
 * @since 0.3.0
 */
public final class Arguments1Validator<A1, X> extends Validator<Arguments1<A1>> {
	private final Arguments1.Mapper<A1, X> mapper;

	public Arguments1Validator(String messageKeySeparator,
			List<ConstraintPredicates<Arguments1<A1>, ?>> constraintPredicates,
			List<CollectionValidator<Arguments1<A1>, ?, ?>> collectionValidators,
			List<Pair<ConstraintCondition<Arguments1<A1>>, ValidatorSubset<Arguments1<A1>>>> conditionalValidators,
			MessageFormatter messageFormatter, Arguments1.Mapper<A1, X> mapper) {
		super(messageKeySeparator, constraintPredicates, collectionValidators,
				conditionalValidators, messageFormatter);
		this.mapper = mapper;
	}

	public Either<ConstraintViolations, X> validateArgs(A1 a1) {
		return this
				.validateToEither(Arguments.of(a1), Locale.getDefault(),
						ConstraintGroup.DEFAULT)
				.rightMap(values -> values.map(this.mapper));
	}

	public Either<ConstraintViolations, X> validateArgs(A1 a1,
			ConstraintGroup constraintGroup) {
		return this
				.validateToEither(Arguments.of(a1), Locale.getDefault(), constraintGroup)
				.rightMap(values -> values.map(this.mapper));
	}

	public Either<ConstraintViolations, X> validateArgs(A1 a1, Locale locale) {
		return this.validateToEither(Arguments.of(a1), locale, ConstraintGroup.DEFAULT)
				.rightMap(values -> values.map(this.mapper));
	}

	public Either<ConstraintViolations, X> validateArgs(A1 a1, Locale locale,
			ConstraintGroup constraintGroup) {
		ConstraintViolations violations = this.validate(Arguments.of(a1), locale,
				constraintGroup);
		if (violations.isValid()) {
			return Either.right(Arguments.of(a1).map(this.mapper));
		}
		else {
			return Either.left(violations);
		}
	}

	public X validated(A1 a1) throws ConstraintViolationsException {
		return this.validateArgs(a1).rightOrElseThrow(ConstraintViolationsException::new);
	}

	public X validated(A1 a1, ConstraintGroup constraintGroup)
			throws ConstraintViolationsException {
		return this.validateArgs(a1, constraintGroup)
				.rightOrElseThrow(ConstraintViolationsException::new);
	}

	public X validated(A1 a1, Locale locale) throws ConstraintViolationsException {
		return this.validateArgs(a1, locale)
				.rightOrElseThrow(ConstraintViolationsException::new);
	}

	public X validated(A1 a1, Locale locale, ConstraintGroup constraintGroup)
			throws ConstraintViolationsException {
		return this.validateArgs(a1, locale, constraintGroup)
				.rightOrElseThrow(ConstraintViolationsException::new);
	}
}