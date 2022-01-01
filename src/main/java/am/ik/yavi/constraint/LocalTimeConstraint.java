package am.ik.yavi.constraint;

import java.time.LocalTime;

import am.ik.yavi.constraint.base.TemporalConstraintBase;

/**
 * This is the actual class for constraints on LocalTime.
 *
 * @author Diego Krupitza
 * @since 0.10.0
 */
public class LocalTimeConstraint<T>
		extends TemporalConstraintBase<T, LocalTime, LocalTimeConstraint<T>> {
	@Override
	protected boolean isAfter(LocalTime a, LocalTime b) {
		return a.isAfter(b);
	}

	@Override
	protected boolean isBefore(LocalTime a, LocalTime b) {
		return a.isBefore(b);
	}

	@Override
	public LocalTimeConstraint<T> cast() {
		return this;
	}
}