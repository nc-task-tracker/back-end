package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.CommentDto;
import com.example.demo.dto.FilterDto;
import com.example.demo.dto.IssueDto;
import com.example.demo.dto.ProfileDto;
import com.example.demo.dto.util.PageDto;
import com.example.demo.dto.util.TableSortParametersDTO;
import com.example.demo.model.Comment;
import com.example.demo.model.Filter;
import com.example.demo.model.Issue;
import com.example.demo.service.CommentService;
import com.example.demo.service.IssueService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/issue")
public class IssueController {
    private IssueService issueService;
    private CommentService commentService;
    private ModelMapper modelMapper;

    @Autowired
    public IssueController(IssueService issueService,
                           CommentService commentService,
                           ModelMapper modelMapper) {
        this.issueService = issueService;
        this.commentService = commentService;
        this.modelMapper = modelMapper;
        modelMapper.addMappings (new PropertyMap<CommentDto, Comment> () {
            @Override
            protected void configure () {
                skip ().getProfile ().setId (null);
            }
        });
    }

    @GetMapping(value = "/{id}")
    public IssueDto getIssueById(@PathVariable(name = "id") String id) {
        return modelMapper.map(issueService.getIssueById(id), IssueDto.class);
    }

    @GetMapping(value = "/all")
    public List<IssueDto> getAllIssues() {
        List<IssueDto> issuesDto = new ArrayList<>();
        List<Issue> issues = issueService.getAllIssues();
        for(Issue item : issues) {
            issuesDto.add(modelMapper.map(item, IssueDto.class));
        }
        return issuesDto;
    }

    @GetMapping(value = "/searchByName")
    public List<IssueDto> getAllIssueName(@RequestParam(required = false) String name) {
        List<IssueDto> issuesDto = new ArrayList<>();
        List<Issue> issues = issueService.getIssueName(name);
        for(Issue item : issues) {
            issuesDto.add(modelMapper.map(item, IssueDto.class));
        }
        return issuesDto;

//        return modelMapper.map(issueService.getIssueName(name), IssueDto.class);
    }

    @GetMapping(value = "/project/{id}")
    public List<IssueDto> getIssuesByProjectId(@PathVariable(name = "id") String id) {

        return issueService.getIssuesByProjectId(id)
                .stream().map(issue -> modelMapper.map(issue,IssueDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/project/{projectId}")
    public IssueDto createIssue( @PathVariable (name = "projectId") String projectId,
                                 /*@Valid*/ @RequestBody IssueDto issue) {
         Issue is = modelMapper.map(issue, Issue.class);
         String assigneeId = issue.getAssignee().getId();
         String reporterId = issue.getReporter();
        return modelMapper.map(issueService.createIssue(projectId, assigneeId, reporterId, is), IssueDto.class);
    }
    @PutMapping(value = "/{id}")
    public IssueDto updateIssue(@PathVariable(name = "id") String id,
                                @RequestBody IssueDto issueDto) {
        issueDto.setId(id);
        return modelMapper.map(issueService.updateIssue(modelMapper.map(issueDto, Issue.class)), IssueDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteIssue(@PathVariable(name = "id") String id) {
        issueService.deleteIssue(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "project/{id}/sort")
    public PageDto<IssueDto> getSortedIssuesByProjectId(@PathVariable(name = "id") String code,
                                              @RequestBody TableSortParametersDTO sortParameters){
        return issueService.getSortedIssuesByProjectId(code,sortParameters);
    }

    @PostMapping(value = "/{id}/saveComment")
    public CommentDto saveComment (@PathVariable(name = "id") String id,
                                   @RequestBody CommentDto commentDto) {

        commentDto.setIssueId(id);
        return modelMapper.map(commentService.saveComment(modelMapper.map(commentDto, Comment.class), commentDto.getProfileId()), CommentDto.class);
    }

    @GetMapping(value = "/allComments")
    public List<CommentDto> getAllComments() {
        List<CommentDto> commentsDto = new ArrayList<>();
        List<Comment> comments = commentService.getAllComments();
        for(Comment item : comments) {
            commentsDto.add(modelMapper.map(item, CommentDto.class));
        }
        return commentsDto;
    }

//    @GetMapping(value = "/{id}")
//    public CommentDto getCommentById(@PathVariable(name = "id") String id) {
//        return modelMapper.map(commentService.getCommentById(id), CommentDto.class);
//    }
//
//    @PutMapping
//    public CommentDto updateComment(@RequestBody CommentDto commentForUpdate) {
//        Comment comment = modelMapper.map(commentService.getCommentById(commentForUpdate.getId()), Comment.class);
//        return modelMapper.map(commentService.updateComment(comment), CommentDto.class);
//    }
//
//    @DeleteMapping(value = "/delete/{id}")
//    public ResponseEntity deleteComment(@PathVariable(name = "id") String id) {
//        commentService.deleteComment(id);
//        return ResponseEntity.noContent().build();
//    }

    @PostMapping(value = "/search/")
    public List<IssueDto> findAllIssuesByPredicates(@RequestBody FilterDto filterDto) {
        List<IssueDto> issuesDto = new ArrayList<>();
        List<Issue> issues = issueService.searchIssue(modelMapper.map(filterDto, Filter.class));
        for(Issue item : issues) {
            issuesDto.add(modelMapper.map(item, IssueDto.class));
        }
        return issuesDto;
    }
}
