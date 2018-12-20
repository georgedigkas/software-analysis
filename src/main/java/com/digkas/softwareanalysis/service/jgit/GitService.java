/**
 * 
 */
package com.digkas.softwareanalysis.service.jgit;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public interface GitService {

	Git gitClone(String uri);

	Iterable<RevCommit> getAllCommitsIterable();

	Ref gitCheckout(String sha);

	Ref gitCheckout(RevCommit revCommit);

	/*
	 * //http://stackoverflow.com/questions/13399990/usage-of-pull-command-in-jgit
	 * PullResult gitPull();
	 * 
	 * FetchResult gitFetch();
	 * 
	 * MergeResult gitMerge();
	 * 
	 * List<RevCommit> getAllCommitsList();
	 * 
	 * List<RevCommit> getDailyCommitsList();
	 * 
	 * List<RevCommit> getWeeklyCommitsList();
	 * 
	 * List<RevCommit> getMonthlyCommitsList();
	 * 
	 * List<RevCommit> getYearlyCommitsList();
	 * 
	 * List<RevCommit> getCommitsListByMode(String mode);
	 */

}
