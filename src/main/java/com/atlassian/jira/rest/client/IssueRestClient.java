package com.atlassian.jira.rest.client;

import com.atlassian.jira.rest.client.domain.*;
import com.atlassian.jira.rest.client.domain.input.*;
import com.atlassian.jira.rest.client.internal.json.CimEditIssuemetaData;
import com.google.common.annotations.Beta;
import com.google.common.collect.Iterables;

import javax.annotation.Nullable;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.Set;

public interface IssueRestClient {

    /**
     * 创建Issue
     *
     * @param issue           IssueInput，包含Field
     * @param progressMonitor ProgressMonitor
     * @return BasicIssue
     */
    BasicIssue createIssue(IssueInput issue, ProgressMonitor progressMonitor);

    /**
     * 批量创建Issue
     *
     * @param issues          BulkIssueInput
     * @param progressMonitor ProgressMonitor
     * @return BulkIssues
     */
    BulkIssues createIssues(BulkIssueInput issues, ProgressMonitor progressMonitor);

    /**
     * 获得Create Issue Meta信息，一班用于项目初始化时，查询Project，IssueType，Field，Option等信息
     *
     * @param options         GetCreateIssueMetadataOptions
     * @param progressMonitor ProgressMonitor
     * @return Iterable<CimProject>
     */
    Iterable<CimProject> getCreateIssueMetadata(@Nullable GetCreateIssueMetadataOptions options, ProgressMonitor progressMonitor);

    /**
     * 获得Issue编辑的相关字段和可选值信息
     *
     * @param issueKey        String
     * @param progressMonitor ProgressMonitor
     * @return CimEditIssuemetaData
     */
    CimEditIssuemetaData getEditIssueMetadata(@Nullable String issueKey, ProgressMonitor progressMonitor);

    /**
     * 通过issueKey查询Issue的信息，包含全部字段
     *
     * @param issueKey        String
     * @param progressMonitor ProgressMonitor
     * @return Issue
     */
    Issue getIssue(String issueKey, ProgressMonitor progressMonitor);

    /**
     * 通过issueKey获取Issue相关字段，通过expand控制显示属性
     *
     * @param issueKey        String
     * @param expand          Iterable<IssueRestClient.Expandos>
     * @param progressMonitor ProgressMonitor
     * @return Issue
     */
    Issue getIssue(String issueKey, Iterable<IssueRestClient.Expandos> expand, ProgressMonitor progressMonitor);

    /**
     * 通过issueKey查询指定field的信息
     *
     * @param issueKey        String
     * @param fields          String
     * @param progressMonitor ProgressMonitor
     * @return Issue
     */
    Issue getIssue(String issueKey, String fields, ProgressMonitor progressMonitor);

    /**
     * 通过issueKey查询指定field和expand的相关信息
     *
     * @param issueKey        String
     * @param fields          String
     * @param expand          Iterable<IssueRestClient.Expandos>
     * @param progressMonitor ProgressMonitor
     * @return Issue
     */
    Issue getIssue(String issueKey, Set<String> fields, Iterable<IssueRestClient.Expandos> expand, ProgressMonitor progressMonitor);

    /**
     * 通过issueKey获取watchers信息
     *
     * @param issueKey        String
     * @param progressMonitor ProgressMonitor
     * @return Watchers
     */
    Watchers getWatchers(String issueKey, ProgressMonitor progressMonitor);

    /**
     * 通过issueKey获取voters信息
     *
     * @param issueKey        String
     * @param progressMonitor progressMonitor
     * @return Votes
     */
    Votes getVotes(String issueKey, ProgressMonitor progressMonitor);

    /**
     * 通过issueKey获取Transition信息
     *
     * @param issueKey        String
     * @param progressMonitor ProgressMonitor
     * @return Iterable<Transition>
     */
    Iterable<Transition> getTransitions(String issueKey, ProgressMonitor progressMonitor);

    /**
     * 通过issueKey获取IssueLinks信息
     *
     * @param issueKey        String
     * @param progressMonitor ProgressMonitor
     * @return Iterable<IssueLink
     */
    Iterable<IssueLink> getIssueLinks(String issueKey, ProgressMonitor progressMonitor);

    /**
     * 改变Issue的transition
     *
     * @param issueKey        String
     * @param transitionId    Integer
     * @param progressMonitor ProgressMonitor
     */
    void transition(String issueKey, Integer transitionId, ProgressMonitor progressMonitor);

    /**
     * 改变Issue的transition
     *
     * @param issueKey        String
     * @param transitionName  String
     * @param progressMonitor ProgressMonitor
     */
    void transition(String issueKey, String transitionName, ProgressMonitor progressMonitor);

    /**
     * 改变Issue的transition，过程中还可以添加Comment，改变Field信息
     *
     * @param issue           Issue
     * @param transitionInput TransitionInput
     * @param progressMonitor ProgressMonitor
     */
    void transition(Issue issue, TransitionInput transitionInput, ProgressMonitor progressMonitor);

    /**
     * 更新Issue信息
     *
     * @param issueKey        String
     * @param fields          Iterable<FieldInput>
     * @param progressMonitor ProgressMonitor
     */
    void updateByKey(String issueKey, Iterable<FieldInput> fields, ProgressMonitor progressMonitor);

    /**
     * 更新Issue信息
     *
     * @param issueId         Long
     * @param fields          Iterable<FieldInput>
     * @param progressMonitor ProgressMonitor
     */
    void updateById(Long issueId, Iterable<FieldInput> fields, ProgressMonitor progressMonitor);

    /**
     * 删除Issue信息
     *
     * @param issueId         Long
     * @param deleteSubtasks  boolean
     * @param progressMonitor ProgressMonitor
     */
    void removeIssue(Long issueId, boolean deleteSubtasks, ProgressMonitor progressMonitor);

    /**
     * 删除Issue信息
     *
     * @param issueKey        String
     * @param deleteSubtasks  boolean
     * @param progressMonitor ProgressMonitor
     */
    void removeIssue(String issueKey, boolean deleteSubtasks, ProgressMonitor progressMonitor);

    /**
     * 删除Issue
     *
     * @param issueKey        String
     * @param progressMonitor ProgressMonitor
     */
    void removeIssue(String issueKey, ProgressMonitor progressMonitor);

    /**
     * 添加Vote
     *
     * @param issueKey        String
     * @param progressMonitor ProgressMonitor
     */
    void vote(String issueKey, ProgressMonitor progressMonitor);

    /**
     * 解除vote
     *
     * @param issueKey        String
     * @param progressMonitor ProgressMonitor
     */
    void unvote(String issueKey, ProgressMonitor progressMonitor);

    /**
     * 设置当前用户watcher该Issue
     *
     * @param issueKey        String
     * @param progressMonitor ProgressMonitor
     */
    void watch(String issueKey, ProgressMonitor progressMonitor);

    /**
     * 解除当前用户watcher该Issue
     *
     * @param issueKey        String
     * @param progressMonitor ProgressMonitor
     */
    void unwatch(String issueKey, ProgressMonitor progressMonitor);

    /**
     * 添加username对该issue的watchers
     *
     * @param issueKey        String
     * @param username        String
     * @param progressMonitor ProgressMonitor
     */
    void addWatcher(String issueKey, String username, ProgressMonitor progressMonitor);

    /**
     * 解除username对该issue的watchers
     *
     * @param issueKey        String
     * @param username        String
     * @param progressMonitor ProgressMonitor
     */
    void removeWatcher(String issueKey, String username, ProgressMonitor progressMonitor);

    /**
     * 添加关联关系
     *
     * @param linkIssuesInput LinkIssuesInput
     * @param progressMonitor ProgressMonitor
     */
    void linkIssue(LinkIssuesInput linkIssuesInput, ProgressMonitor progressMonitor);

    /**
     * 删除关联关系
     *
     * @param linkUri         URI
     * @param progressMonitor ProgressMonitor
     */
    void unlinkIssue(URI linkUri, ProgressMonitor progressMonitor);

    /**
     * 添加附件
     *
     * @param progressMonitor ProgressMonitor
     * @param issueKey        String
     * @param inputStream     InputStream
     * @param filename        filename
     */
    void addAttachment(ProgressMonitor progressMonitor, String issueKey, InputStream inputStream, String filename);

    /**
     * 添加附件
     *
     * @param progressMonitor ProgressMonitor
     * @param issueKey        String
     * @param attachments     AttachmentInput...
     */
    void addAttachments(ProgressMonitor progressMonitor, String issueKey, AttachmentInput... attachments);

    /**
     * 添加附件
     *
     * @param progressMonitor ProgressMonitor
     * @param issueKey        String
     * @param files           File...
     */
    void addAttachments(ProgressMonitor progressMonitor, String issueKey, File... files);

    /**
     * 删除附件
     *
     * @param progressMonitor ProgressMonitor
     * @param attachmentUrl   URI
     */
    void removeAttachment(ProgressMonitor progressMonitor, URI attachmentUrl);

    /**
     * 删除附件
     *
     * @param progressMonitor ProgressMonitor
     * @param attachmentId    Integer
     */
    void removeAttachment(ProgressMonitor progressMonitor, Integer attachmentId);

    /**
     * 添加Comment
     *
     * @param progressMonitor ProgressMonitor
     * @param issueKey        String
     * @param comment         Comment
     */
    void addComment(ProgressMonitor progressMonitor, String issueKey, Comment comment);

    /**
     * 删除Comment
     *
     * @param progressMonitor ProgressMonitor
     * @param issueKey        String
     * @param commentId       Long
     */
    void removeComment(ProgressMonitor progressMonitor, String issueKey, Long commentId);

    /**
     * 更新Comment
     *
     * @param progressMonitor ProgressMonitor
     * @param issueKey        String
     * @param comment         Comment
     */
    void updateComment(ProgressMonitor progressMonitor, String issueKey, Comment comment);

    /**
     * 获得附件
     *
     * @param progressMonitor ProgressMonitor
     * @param attachmentUrl   URI
     * @return
     */
    InputStream getAttachment(ProgressMonitor progressMonitor, URI attachmentUrl);

    void addWorklog(URI var1, WorklogInput var2, ProgressMonitor progressMonitor);

    public static enum Expandos {
        CHANGELOG("changelog"),
        SCHEMA("schema"),
        NAMES("names"),
        TRANSITIONS("transitions"),
        RENDERED_FIELDS("renderedFields"),
        EDITMETA("editmeta");

        private final String fieldName;

        private Expandos(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldName() {
            return this.fieldName;
        }
    }
}
