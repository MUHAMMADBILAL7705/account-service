package com.account.transformer;

public interface Transformer<M,E> {

    M toModel(E e);

    E toEntity(M e);
}
