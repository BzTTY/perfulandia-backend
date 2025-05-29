package com.perfulandia.reviewservice.service;

import com.perfulandia.reviewservice.model.Review;
import com.perfulandia.reviewservice.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository repo;

    public ReviewService(ReviewRepository repo) {
        this.repo = repo;
    }

    public List<Review> listar() {
        return repo.findAll();
    }

    public Review guardar(Review review) {
        return repo.save(review);
    }

    public Review buscarPorId(long id) {
        return repo.findById(id).orElse(null);
    }

    public void eliminar(long id) {
        repo.deleteById(id);
    }
}
