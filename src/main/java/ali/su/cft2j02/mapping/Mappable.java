package ali.su.cft2j02.mapping;


public interface Mappable<T, R> {
    R map(T obj);
}