/**
 * Copyright (c) 2011 Source Auditor Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
*/
package org.spdx.library.model.license;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spdx.library.InvalidSPDXAnalysisException;
import org.spdx.library.model.ModelObject;
import org.spdx.storage.IModelStore;

/**
 * This abstract class represents several ways of describing licensing information.
 * License info can be described as a set of conjunctive licenses (where all licenses
 * terms must apply), a set of disjunctive licenses (where there is a choice of one
 * license among the set described) or a specific license.  The specific licenses
 * are of a SimpleLicensingInfoType
 * @author Gary O'Neall
 *
 */
public abstract class AnyLicenseInfo extends ModelObject {
	
	static final Logger logger = LoggerFactory.getLogger(AnyLicenseInfo.class.getName());

	/**
	 * Create a new LicenseInfo object
	 * @param modelStore container which includes the license
	 * @param documentUri URI for the SPDX document containing the license
	 * @param id identifier for the license
	 * @param create if true, create the license if it does not exist
	 * @throws InvalidSPDXAnalysisException 
	 */
	AnyLicenseInfo(IModelStore modelStore, String documentUri, String id, boolean create) throws InvalidSPDXAnalysisException {
		super(modelStore, documentUri, id, create);
	}
	
	// force subclasses to implement toString
	@Override
    public abstract String toString();
}