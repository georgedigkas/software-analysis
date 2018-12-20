/**
 * 
 */
package com.digkas.git.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digkas.git.domain.Commit;
import com.digkas.git.domain.Method;
import com.digkas.git.domain.Project;
import com.digkas.git.persistence.MethodRepository;
import com.google.common.collect.Lists;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Service
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MethodServiceBean implements MethodService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MethodRepository methodRepository;

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#findAll()
	 */
	@Override
	public Collection<Method> findAll() {
		logger.info("> findAll");

		Collection<Method> methods = methodRepository.findAll();

		logger.info("< findAll");
		return methods;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#findOne(java.lang.Integer)
	 */
	@Override
	//	@Cacheable(value = "methods", key = "#id")
	public Method findOne(Integer id) {
		logger.info("> findOne id:{}", id);

		Method method = methodRepository.findOne(id);

		logger.info("< findOne id:{}", id);
		return method;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#create(com.digkas.git.domain.Method)
	 */
	@Override
	//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	//	@CachePut(value = "methodsCommit", key = "#commitId")
	public Method create(Method method) {
		logger.info("> create Method id:{}", method.getId());

		Method savedMethod = methodRepository.save(method);

		logger.info("< create Method id:{}", method.getId());
		return savedMethod;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#create(java.util.List)
	 */
	@Override
	//	@CachePut(value = "methodsCommit", key = "#commitId")
	public List<Method> create(List<Method> methods) {
		logger.info("> create methods");

		//List<Method> savedMethods = methodRepository.save(methods);
		List<Method> savedMethods = new ArrayList<>();
		for (List<Method> entities : Lists.partition(methods, 1000))
			savedMethods.addAll(methodRepository.save(entities));

		logger.info("< create methods");
		return savedMethods;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#update(com.digkas.git.domain.Method)
	 */
	@Override
	//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	//	@CachePut(value = "methods", key = "#id")
	public Method update(Method method) {
		logger.info("> update Method id:{}", method.getId());

		Method methodToUpdate = findOne(method.getId());
		if (methodToUpdate == null)
			return null;

		Method updatedMethod = methodRepository.save(method);

		logger.info("< update Method id:{}", method.getId());
		return updatedMethod;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#update(java.util.List)
	 */
	@Override
	//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	//	@CachePut(value = "methodsCommit", key = "#commitId")
	public List<Method> update(List<Method> methods) {
		logger.info("> update Methods");
		List<Method> methodsToBeUpdated = new ArrayList<>();

		for (Method method : methods) {
			Method methodToUpdate = findOne(method.getId());
			if (Objects.nonNull(methodToUpdate))
				methodsToBeUpdated.add(method);
		}

		List<Method> updatedMethods = methodRepository.save(methodsToBeUpdated);

		logger.info("< update Methods");
		return updatedMethods;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#delete(java.lang.Integer)
	 */
	@Override
	//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Integer id) {
		logger.info("> delete id: {}", id);

		methodRepository.delete(id);

		logger.info("< delete id: {}", id);
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#delete(java.lang.Iterable)
	 */
	@Override
	//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Iterable<Method> methods) {
		logger.info("> delete Iterable");

		methodRepository.deleteInBatch(methods);

		logger.info("< delete Iterable");
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#findByIdProjectId(com.digkas.git.domain.Project)
	 */
	@Override
	public Collection<Method> findByIdProjectId(Project projectId) {
		logger.info("> findByIdProjectId projectId: {}", projectId);

		Collection<Method> methods = methodRepository.findByIdProjectId(projectId);

		logger.info("< findByIdProjectId projectId: {}", projectId);
		return methods;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#findByIdProjectKee(java.lang.String)
	 */
	@Override
	public Collection<Method> findByIdProjectKee(String kee) {
		logger.info("> findByIdProjectKee Kee: {}", kee);

		Collection<Method> methods = methodRepository.findByIdProjectKee(kee);

		logger.info("< findByIdProjectKee Kee: {}", kee);
		return methods;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#findByCommitId(com.digkas.git.domain.Commit)
	 */
	@Override
	//	@Cacheable(value = "methodsCommit", key = "#commitId")
	public Collection<Method> findByCommitId(Commit commitId) {
		logger.info("> findByCommitId: {}", commitId);

		Collection<Method> methods = methodRepository.findByCommitId(commitId);

		logger.info("< findByCommitId: {}", commitId);
		return methods;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#findByCommitId(java.lang.Integer)
	 */
	@Override
	public Collection<Method> findByCommitId(Integer commitId) {
		logger.info("> findByCommitId: {}", commitId);

		Collection<Method> methods = methodRepository.findByCommitId(commitId);

		logger.info("< findByCommitId: {}", commitId);
		return methods;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#findByCommitIdNative(java.lang.Integer)
	 */
	@Override
	public Collection<Method> findByCommitIdNative(Integer commitId) {
		logger.info("> findByCommitIdNative: {}", commitId);

		Collection<Method> methods = methodRepository.findByCommitIdNative(commitId);

		logger.info("< findByCommitIdNative: {}", commitId);
		return methods;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#findMethodsWithNullOldMethodIdByProjectId(com.digkas.git.domain.Project)
	 */
	@Override
	public Collection<Method> findMethodsWithNullOldMethodIdByProjectId(Project projectId) {
		logger.info("> findByMethodsWithNullOldMethodIdProjectId: {}", projectId);

		Collection<Method> methods = methodRepository.findMethodsWithNullOldMethodIdByProjectId(projectId);

		logger.info("< findMethodsWithNullOldMethodIdByProjectId: {}", projectId);
		return methods;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#findMethodsWithNullOldMethodIdByCommitId(com.digkas.git.domain.Commit)
	 */
	@Override
	public Collection<Method> findMethodsWithNullOldMethodIdByCommitId(Commit commitId) {
		logger.info("> findMethodsWithNullOldMethodIdByCommitId: {}", commitId);

		Collection<Method> methods = methodRepository.findMethodsWithNullOldMethodIdByCommitId(commitId);

		logger.info("< findMethodsWithNullOldMethodIdByProjectIdANDCommitId: {}", commitId);
		return methods;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#findMethodsWithNotNullOldMethodIdByCommitId(com.digkas.git.domain.Commit)
	 */
	@Override
	public Collection<Method> findMethodsWithNotNullOldMethodIdByCommitId(Commit commitId) {
		logger.info("> findMethodsWithNotNullOldMethodIdByProjectIdANDCommitId: {}", commitId);

		Collection<Method> methods = methodRepository.findMethodsWithNotNullOldMethodIdByCommitId(commitId);

		logger.info("< findMethodsWithNotNullOldMethodIdByProjectIdANDCommitId: {}", commitId);
		return methods;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#findUnchangedMethodsWithNullOldMethodIdByCommitId(com.digkas.git.domain.Commit)
	 */
	@Override
	public Collection<Method> findUnchangedMethodsWithNullOldMethodIdByCommitId(Commit commitId) {
		logger.info("> findUnchangedMethodsWithNullOldMethodIdByCommitId: {}", commitId);

		Collection<Method> methods = this.findUnchangedMethodsWithNullOldMethodIdByCommitId(commitId)
				.stream()
				.filter(method -> method.getClassifier().endsWith("-Unchanged"))
				.collect(Collectors.toList());

		logger.info("< findUnchangedMethodsWithNullOldMethodIdByCommitId: {}", commitId);
		return methods;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#deleteByCommitId(com.digkas.git.domain.Commit)
	 */
	@Override
	public int deleteByCommitId(Commit commitId) {
		logger.info("> deleteByCommitId: {}", commitId);

		int methodsDeleted = methodRepository.deleteByCommitId(commitId);

		logger.info("< deleteByCommitId: {}", commitId);
		return methodsDeleted;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#deleteByCommitId(java.lang.Integer)
	 */
	@Override
	public int deleteByCommitId(Integer commitId) {
		logger.info("> deleteByCommitId: {}", commitId);

		int methodsDeleted = methodRepository.deleteByCommitId(commitId);

		logger.info("< deleteByCommitId: {}", commitId);
		return methodsDeleted;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#deleteByCommitId(java.lang.String)
	 */
	@Override
	public int deleteByCommitId(String sha) {
		logger.info("> deleteByCommitId: {}", sha);

		int methodsDeleted = methodRepository.deleteByCommitId(sha);

		logger.info("< deleteByCommitId: {}", sha);
		return methodsDeleted;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#findNotNullOldMethodIdsByCommitId(com.digkas.git.domain.Commit)
	 */
	@Override
	public Set<Integer> findNotNullOldMethodIdsByCommitId(Commit commitId) {
		logger.info("> findNotNullOldMethodIdsByCommitId: {}", commitId);

		Set<Integer> methods = methodRepository.findNotNullOldMethodIdsByCommitId(commitId);

		logger.info("< findNotNullOldMethodIdsByCommitId: {}", commitId);
		return methods;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#findFromSonarqubeIssuesByCommitIdANDkees(com.digkas.git.domain.Commit, java.util.Collection)
	 */
	@Override
	public Set<Method> findFromSonarqubeIssuesByCommitIdANDkees(Commit commitId, Collection<String> kees) {
		logger.info("> findFromSonarqubeIssuesByCommitIdANDkees: {}, {}", commitId, kees);

		Set<Method> methods = methodRepository.findFromSonarqubeIssuesByCommitIdANDkees(commitId, kees);

		logger.info("< findFromSonarqubeIssuesByCommitIdANDkees: {}, {}", commitId, kees);
		return methods;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#findFromSonarqubeIssuesByCommitIdANDMethodNameANDkees(com.digkas.git.domain.Commit, java.lang.String, java.util.Collection)
	 */
	@Override
	public Set<Method> findFromSonarqubeIssuesByCommitIdANDMethodNameANDkees(Commit commitId, String methodName, Collection<String> kees) {
		logger.info("> findFromSonarqubeIssuesByCommitIdANDMethodNameANDkees: {}, {}, {}", commitId, methodName, kees);

		Set<Method> methods = methodRepository.findFromSonarqubeIssuesByCommitIdANDMethodNameANDkees(commitId, methodName, kees);

		logger.info("< findFromSonarqubeIssuesByCommitIdANDMethodNameANDkees: {}, {}, {}", commitId, methodName, kees);
		return methods;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.MethodService#findFromSonarqubeIssuesByCommitIdANDPathANDkees(com.digkas.git.domain.Commit, java.lang.String, java.util.Collection)
	 */
	@Override
	public Set<Method> findFromSonarqubeIssuesByCommitIdANDPathANDkees(Commit commitId, String path, Collection<String> kees) {
		logger.info("> findFromSonarqubeIssuesByCommitIdANDPathANDkees: {}, {}, {}", commitId, path, kees);

		Set<Method> methods = methodRepository.findFromSonarqubeIssuesByCommitIdANDPathANDkees(commitId, path, kees);

		logger.info("< findFromSonarqubeIssuesByCommitIdANDPathANDkees: {}, {}, {}", commitId, path, kees);
		return methods;
	}

}
