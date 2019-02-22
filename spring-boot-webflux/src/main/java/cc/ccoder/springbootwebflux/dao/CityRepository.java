package cc.ccoder.springbootwebflux.dao;

import cc.ccoder.springbootwebflux.entity.City;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author chencong(ccoder.cc)
 * @since 2019/2/22 16:22
 */
@Repository
public class CityRepository {
    /**
     * 假装自己有个数据库
     */
    private ConcurrentMap<Long, City> repository = new ConcurrentHashMap<>();
    /**
     * id生成器
     */
    private static AtomicLong idGenerator = new AtomicLong(0);

    public Long save(City city) {
        Long id = idGenerator.incrementAndGet();
        city.setId(id);
        repository.putIfAbsent(id, city);
        return id;
    }

    public Collection<City> findAll() {
        return repository.values();
    }

    public City findCityById(Long id) {
        return repository.get(id);
    }

    public Long updateCity(City city) {
        repository.put(city.getId(), city);
        return city.getId();
    }

    public Long deleteCity(Long id) {
        repository.remove(id);
        return id;
    }

}
