package br.com.etyllica.commons.context.load;

public interface LoaderListener<T> {
    void onLoad(T context);
}
