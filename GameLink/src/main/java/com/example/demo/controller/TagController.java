package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Tag;
import com.example.demo.service.TagService;

@RestController
@RequestMapping("/tag")
public class TagController {
	@Autowired(required = true)
	TagService tagService;

	@GetMapping("/all")
	public List<Tag> listAllTags() {
		return tagService.getAll();
	}

	@PostMapping("/add")
	public Tag saveTag(@RequestBody Tag tag) {
		return tagService.add(tag);
	}

	@GetMapping("/{id}")
	public Tag getOneTag(@PathVariable(name = "id") int id) {
		return tagService.getOne(id);
	}

	@PutMapping("/{id}")
	public Tag updateTag(@PathVariable(name = "id") int id, @RequestBody Tag tag) {

		Tag prevTag = new Tag();
		Tag newTag = new Tag();

		prevTag = tagService.getOne(id);

		prevTag.setName(tag.getName());
		prevTag.setDescription(tag.getDescription());

		newTag = tagService.update(prevTag);

		return newTag;
	}

	@DeleteMapping("/{id}")
	public void deleteTag(@PathVariable(name = "id") int id) {
		tagService.deleteOne(id);
	}

}
