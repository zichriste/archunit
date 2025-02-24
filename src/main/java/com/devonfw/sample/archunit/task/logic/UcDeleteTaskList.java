package com.devonfw.sample.archunit.task.logic;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devonfw.sample.archunit.task.common.TaskListEto;
import com.devonfw.sample.archunit.task.dataaccess.TaskListRepository;

/**
 * Use-Case to delete {@link com.devonfw.sample.archunit.task.common.TaskList}s.
 */
@ApplicationScoped
@Named
@Transactional
public class UcDeleteTaskList {

  private static final Logger LOG = LoggerFactory.getLogger(UcDeleteTaskList.class);

  @Inject
  TaskListRepository taskListRepository;

  /**
   * @param id the {@link com.devonfw.sample.archunit.task.dataaccess.TaskListEntity#getId() primary key} of the
   *        {@link com.devonfw.sample.archunit.task.dataaccess.TaskListEntity} to delete.
   */
  // @RolesAllowed(ApplicationAccessControlConfig.PERMISSION_DELETE_TASK_ITEM)
  public void delete(Long id) {

    this.taskListRepository.deleteById(id);
  }

  /**
   * @param list the {@link TaskListEto} to delete.
   */
  // @RolesAllowed(ApplicationAccessControlConfig.PERMISSION_DELETE_TASK_ITEM)
  public void delete(TaskListEto list) {

    Long id = list.getId();
    if (id == null) {
      LOG.info("TaskItem {} ist transient und kann nicht gelöscht werden", list.getTitle());
    }
    this.taskListRepository.deleteById(id);
  }

}
