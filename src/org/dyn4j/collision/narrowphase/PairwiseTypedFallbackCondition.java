/*
 * Copyright (c) 2010-2013 William Bittle  http://www.dyn4j.org/
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted 
 * provided that the following conditions are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice, this list of conditions 
 *     and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice, this list of conditions 
 *     and the following disclaimer in the documentation and/or other materials provided with the 
 *     distribution.
 *   * Neither the name of dyn4j nor the names of its contributors may be used to endorse or 
 *     promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR 
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND 
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL 
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER 
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT 
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.dyn4j.collision.narrowphase;

import org.dyn4j.geometry.Convex;

/**
 * Represents a {@link TypedFallbackCondition} that filters on a particular pair of types.
 * <p>
 * If the pair {@link Convex} types match this pair, then the condition is met.
 * @author William Bittle
 * @version 3.1.5
 * @since 3.1.5
 */
public class PairwiseTypedFallbackCondition extends TypedFallbackCondition implements FallbackCondition, Comparable<FallbackCondition> {
	/** The first type to compare to */
	protected Class<? extends Convex> type1;
	
	/** True if strict type matching should be performed on the first type */
	protected boolean strict1;
	
	/** The second type to compare to */
	protected Class<? extends Convex> type2;

	/** True if strict type matching should be performed on the second type */
	protected boolean strict2;
	
	/**
	 * Default constructor.
	 * <p>
	 * The ordering of the types doesn't matter.
	 * <p>
	 * The type matching defaults to strict for both types.
	 * @param type1 the first type of the pair
	 * @param type2 the second type of the pair
	 */
	public PairwiseTypedFallbackCondition(Class<? extends Convex> type1, Class<? extends Convex> type2) {
		this(type1, true, type2, true, 0);
	}
	
	/**
	 * Optional constructor.
	 * <p>
	 * The ordering of the types doesn't matter.
	 * <p>
	 * The type matching defaults to strict for both types.
	 * @param type1 the first type of the pair
	 * @param type2 the second type of the pair
	 * @param sortIndex the sort index of this condition
	 */
	public PairwiseTypedFallbackCondition(Class<? extends Convex> type1, Class<? extends Convex> type2, int sortIndex) {
		this(type1, true, type2, true, sortIndex);
	}
	
	
	/**
	 * Optional constructor.
	 * <p>
	 * The ordering of the types doesn't matter.
	 * @param type1 the first type of the pair
	 * @param type2 the second type of the pair
	 * @param strict true if a strict type comparison should be performed for both types
	 */
	public PairwiseTypedFallbackCondition(Class<? extends Convex> type1, Class<? extends Convex> type2, boolean strict) {
		this(type1, strict, type2, strict, 0);
	}

	/**
	 * Optional constructor.
	 * <p>
	 * The ordering of the types doesn't matter.
	 * @param type1 the first type of the pair
	 * @param type2 the second type of the pair
	 * @param strict true if a strict type comparison should be performed on both types
	 * @param sortIndex the sort index of this condition
	 */
	public PairwiseTypedFallbackCondition(Class<? extends Convex> type1, Class<? extends Convex> type2, boolean strict, int sortIndex) {
		this(type1, strict, type2, strict, sortIndex);
	}

	/**
	 * Optional constructor.
	 * <p>
	 * The ordering of the types doesn't matter.
	 * @param type1 the first type of the pair
	 * @param strict1 true if a strict type comparison should be performed on the first type
	 * @param type2 the second type of the pair
	 * @param strict2 true if a strict type comparison should be performed on the second type
	 */
	public PairwiseTypedFallbackCondition(Class<? extends Convex> type1, boolean strict1, Class<? extends Convex> type2, boolean strict2) {
		this(type1, strict1, type2, strict2, 0);
	}

	/**
	 * Full constructor.
	 * <p>
	 * The ordering of the types doesn't matter.
	 * @param type1 the first type of the pair
	 * @param strict1 true if a strict type comparison should be performed on the first type
	 * @param type2 the second type of the pair
	 * @param strict2 true if a strict type comparison should be performed on the second type
	 * @param sortIndex the sort index of this condition
	 */
	public PairwiseTypedFallbackCondition(Class<? extends Convex> type1, boolean strict1, Class<? extends Convex> type2, boolean strict2, int sortIndex) {
		super(sortIndex);
		this.type1 = type1;
		this.strict1 = strict1;
		this.type2 = type2;
		this.strict2 = strict2;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (obj instanceof PairwiseTypedFallbackCondition) {
			PairwiseTypedFallbackCondition pfc = (PairwiseTypedFallbackCondition)obj;
			// the types must be equal
			if (((pfc.type1 == this.type1 && pfc.type2 == this.type2) || 
				 (pfc.type1 == this.type2 && pfc.type2 == this.type1)) &&
				 // and their strictness must be equal
			      pfc.strict1 == this.strict1 && pfc.strict2 == this.strict2 &&
			      pfc.sortIndex == this.sortIndex) {
				return true;
			}
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.dyn4j.extras.TypedFallbackCondition#isMatch(java.lang.Class, java.lang.Class)
	 */
	public boolean isMatch(Class<? extends Convex> type1, Class<? extends Convex> type2) {
		if (this.strict1) {
			if (this.strict2) {
				// don't do subclass matching
				return (this.type1 == type1 && this.type2 == type2) || 
					   (this.type1 == type2 && this.type2 == type1);
			} else {
				// only type1 is strict
				return (this.type1 == type1 && this.type2.isAssignableFrom(type2)) || 
					   (this.type1 == type2 && this.type2.isAssignableFrom(type1));
			}
		} else {
			if (this.strict2) {
				// only type 2 is strict
				return (this.type1.isAssignableFrom(type1) && this.type2 == type2) || 
					   (this.type1.isAssignableFrom(type2) && this.type2 == type1);
			} else {
				// allow subclass matching on both types
				return (this.type1.isAssignableFrom(type1) && this.type2.isAssignableFrom(type2)) ||
					   (this.type1.isAssignableFrom(type2) && this.type2.isAssignableFrom(type1));
			}
		}
	}
}
