package com.matthewtamlin.shamir.corecrypto.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.NotNull;

import javax.annotation.Nonnull;
import java.math.BigInteger;

/**
 * A single share produced by sharing a secret with Shamir's Secret Sharing.
 * <p>
 * A Share consists of an index and a value. In the context of Shamir's secret sharing, the index and the value
 * correspond to the point (index, value) in a finite field.
 */
@AutoValue
public abstract class Share {
    /**
     * @return the index of the share, not null
     */
    @SerializedName("index")
    public abstract BigInteger getIndex();
    
    /**
     * @return the value of the share, not null
     */
    @SerializedName("value")
    public abstract BigInteger getValue();
    
    /**
     * @return a new {@link Builder}
     */
    @Nonnull
    public static Builder builder() {
        return new $AutoValue_Share.Builder();
    }
    
    /**
     * Provides a type adapter for serialising this class with Gson.
     *
     * @param gson
     *         a Gson instance, not null
     *
     * @return the type adapter factory, not null
     */
    @Nonnull
    public static TypeAdapter<Share> typeAdapter(@NotNull final Gson gson) {
        return new AutoValue_Share.GsonTypeAdapter(gson);
    }
    
    @AutoValue.Builder
    public static abstract class Builder {
        /**
         * Sets the index of this share. The index must: <ul><li>Not be null.</li><li>Be greater than or equal to
         * 1.</li></ul>
         *
         * @param index
         *         the index
         *
         * @return this builder, not null
         */
        public abstract Builder setIndex(BigInteger index);
        
        /**
         * Sets the value of this share. The value must not be null.
         *
         * @param value
         *         the value
         *
         * @return this builder, not null
         */
        public abstract Builder setValue(BigInteger value);
        
        abstract Share autoBuild();
        
        /**
         * Sets the index of this share. The index must be greater than or equal to 1.
         *
         * @param index
         *         the index
         *
         * @return this builder, not null
         */
        @NotNull
        public Builder setIndex(final long index) {
            return setIndex(BigInteger.valueOf(index));
        }
        
        /**
         * Sets the value of this share.
         *
         * @param value
         *         the value
         *
         * @return this builder, not null
         */
        @NotNull
        public Builder setValue(final long value) {
            return setValue(BigInteger.valueOf(value));
        }
        
        /**
         * Constructs a {@link Share} based on this builder. This method will fail if any of the properties were never
         * set or were set to invalid values (see the documentation of each method for specifics).
         *
         * @return an immutable Share based on this builder, not null
         *
         * @throws IllegalStateException
         *         if any of the values are missing or invalid
         */
        @NotNull
        public Share build() {
            final Share share = autoBuild();
            
            if (share.getIndex().compareTo(BigInteger.ONE) < 0) {
                throw new IllegalStateException("The index must be at least 1.");
            }
            
            return share;
        }
    }
}