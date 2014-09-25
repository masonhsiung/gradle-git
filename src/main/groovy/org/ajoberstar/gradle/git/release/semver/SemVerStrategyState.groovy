/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ajoberstar.gradle.git.release.semver

import groovy.transform.Immutable

import com.github.zafarkhaja.semver.Version

import org.ajoberstar.gradle.git.release.base.NearestVersion
import org.ajoberstar.grgit.Branch
import org.ajoberstar.grgit.Commit

@Immutable(copyWith=true)
final class SemVerStrategyState {
	ChangeScope scopeFromProp
	String stageFromProp
	Commit currentHead
	Branch currentBranch
	boolean repoDirty
	NearestVersion nearestVersion
	String inferredNormal
	String inferredPreRelease
	String inferredBuildMetadata

	Version toVersion() {
		return new Version.Builder().with {
			if (inferredNormal) {
				normalVersion = inferredNormal
			}
			if (inferredPreRelease) {
				preReleaseVersion = inferredPreRelease
			}
			if (inferredBuildMetadata) {
				buildMetadata = inferredBuildMetadata
			}
			build()
		}
	}
}