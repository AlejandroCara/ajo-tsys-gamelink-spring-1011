package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Tag;
import com.example.demo.service.TagService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/tag")
public class TagController {
	@Autowired(required = true)
	TagService tagService;

	@GetMapping("/all")
	public ResponseEntity<List<Tag>> listAllTags(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Page<Tag> tagPage = tagService.getPaginateAll(PageRequest.of(page, size));
		List<Tag> result = tagPage.getContent().stream().map(this::convertToDTO).collect(Collectors.toList());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/add")
	public Tag saveTag(@RequestBody Tag tag) {
		return tagService.add(tag);
	}

	@GetMapping("/id/{id}")
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

	private Tag convertToDTO(Tag tag) {
		return new Tag(tag.getId(), tag.getName(), tag.getDescription(), tag.getParty());
	}

}
