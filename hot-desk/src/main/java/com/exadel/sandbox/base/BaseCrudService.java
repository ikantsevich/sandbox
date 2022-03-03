package com.exadel.sandbox.base;

import com.exadel.sandbox.exception.EntityNotFoundException;
import com.google.common.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Type;
import java.util.List;

@RequiredArgsConstructor
public abstract class BaseCrudService<ENTITY, RESPONSE, UPDATE, CREATE, REPOSITORY extends JpaRepository<ENTITY, Long>> {

    private final Type responseType = new TypeToken<RESPONSE>(getClass()){}.getType();
    private final Type entityType = new TypeToken<ENTITY>(getClass()) {}.getType();
    private final Type responseListType = new TypeToken<List<RESPONSE>>(getClass()) {}.getType();

    protected final ModelMapper mapper;
    protected final REPOSITORY repository;

    public ResponseEntity<List<RESPONSE>> getList() {
        return ResponseEntity.ok(mapper.map(repository.findAll(), responseListType));
    }

    public ResponseEntity<RESPONSE> getById(Long id) {
        ENTITY entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
        RESPONSE response = mapper.map(entity, responseType);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<RESPONSE> update(Long id, UPDATE update) {
        ENTITY entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
        mapper.map(update, entity);
        RESPONSE response = mapper.map(repository.save(entity), responseType);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<RESPONSE> create(CREATE create) {
        ENTITY entity = mapper.map(create, entityType);
        RESPONSE response = mapper.map(repository.save(entity), responseType);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
