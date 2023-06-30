package ru.netology.repository;


import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
  private final ConcurrentHashMap<Long, Post> postsRepository = new ConcurrentHashMap<>();
  static AtomicLong countId = new AtomicLong(1);

  public List<Post> all() {
    return new ArrayList<>(postsRepository.values());
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(postsRepository.get(id));
  }

  public Post save(Post post) throws NotFoundException {

    if (post.getId() == 0) {
      post.setId(countId.get());
      countId.getAndIncrement();

    } else if (postsRepository.containsKey(post.getId())) {
      postsRepository.put(post.getId(), post);
    } else throw new NotFoundException("Такого поста нет");
    return post;
  }

  public void removeById(long id) throws NotFoundException {
    if (postsRepository.containsKey(id)) {
      postsRepository.remove(id);
    } else {
      throw new NotFoundException("Такого поста нет");
    }
  }
}