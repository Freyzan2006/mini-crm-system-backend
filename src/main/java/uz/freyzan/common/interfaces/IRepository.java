package uz.freyzan.common.interfaces;

public interface IRepository<T> {
    T save(T t);
    T update(T t);
    T delete(T t);
    T findById(Long id);
    Iterable<T> findAll();
}