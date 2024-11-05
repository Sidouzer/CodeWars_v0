package group_4.galaxyMyAdmin.Services;

import java.util.Collection;

public interface Service <T>{

    public T findById(Long id);

    public Collection<T> findAll();

    public void save(T obj);
}
