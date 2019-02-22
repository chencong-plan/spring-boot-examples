package cc.ccoder.springbootwebflux.handler;

import cc.ccoder.springbootwebflux.dao.CityRepository;
import cc.ccoder.springbootwebflux.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author chencong(ccoder.cc)
 * @since 2019/2/22 16:28
 */
@Component
public class CityHandler {

    private final CityRepository cityRepository;

    @Autowired
    public CityHandler(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**
     * 保存城市数据
     *
     * @param city
     * @return
     */
    public Mono<Long> save(City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityRepository.save(city)));
    }

    /**
     * 通过城市id查询城市
     *
     * @param id
     * @return
     */
    public Mono<City> findCityById(Long id) {
        return Mono.justOrEmpty(cityRepository.findCityById(id));
    }

    /**
     * 查询所有城市数据
     *
     * @return
     */
    public Flux<City> findAllCity() {
        return Flux.fromIterable(cityRepository.findAll());
    }

    /**
     * 修改城市数据
     *
     * @param city
     * @return
     */
    public Mono<Long> modifyCity(City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityRepository.updateCity(city)));
    }

    /**
     * 根据城市id删除信息
     * @param id
     * @return
     */
    public Mono<Long> deleteCity(Long id) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityRepository.deleteCity(id)));
    }
}
