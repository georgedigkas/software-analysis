/**
 * 
 */
package com.digkas.sonarqube.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digkas.sonarqube.domain.FileSources;
import com.digkas.sonarqube.persistence.FileSourcesRepository;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Service
public class FileSourcesServiceBean implements FileSourcesService {

	@Autowired
	private FileSourcesRepository fileSourcesRepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.FileSourcesService#findAll()
	 */
	@Override
	public Collection<FileSources> findAll() {
		logger.info("> findAll");

		Collection<FileSources> fileSources = fileSourcesRepository.findAll();

		logger.info("< findAll");
		return fileSources;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.FileSourcesService#findOne(java.lang.Integer)
	 */
	@Override
	public FileSources findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.FileSourcesService#create(com.digkas.softwareanalysis.domain.sonarqube.v671.FileSources)
	 */
	@Override
	public FileSources create(FileSources fileSource) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.FileSourcesService#update(com.digkas.softwareanalysis.domain.sonarqube.v671.FileSources)
	 */
	@Override
	public FileSources update(FileSources fileSource) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.FileSourcesService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.FileSourcesService#findByProjectUuid(java.lang.String)
	 */
	@Override
	public Collection<FileSources> findByProjectUuid(String projectUuid) {
		logger.info("> findByProjectUuid");

		Collection<FileSources> fileSources = fileSourcesRepository.findByProjectUuid(projectUuid);

		logger.info("< findByProjectUuid");
		return fileSources;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.FileSourcesService#findByProjectUuidGroupedByRevision(java.lang.String)
	 */
	@Override
	public Map<Optional<String>, List<FileSources>> findByProjectUuidGroupedByRevision(String projectUuid) {
		logger.info("> findByProjectUuidGroupedByRevision");

		Map<Optional<String>, List<FileSources>> fileSourcesGroupedByRevision = this.findByProjectUuid(projectUuid).stream().collect(Collectors.groupingBy(fileSources -> Optional.ofNullable(fileSources.getRevision())));

		logger.info("< findByProjectUuidGroupedByRevision");
		return fileSourcesGroupedByRevision;
	}

}
