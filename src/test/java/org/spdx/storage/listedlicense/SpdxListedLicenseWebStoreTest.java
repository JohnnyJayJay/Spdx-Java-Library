/**
 * Copyright (c) 2019 Source Auditor Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
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
 */
package org.spdx.storage.listedlicense;

import java.util.List;

import org.spdx.library.InvalidSPDXAnalysisException;
import org.spdx.library.SpdxConstants;
import org.spdx.library.model.SpdxModelFactory;
import org.spdx.library.model.license.SpdxListedLicense;
import org.spdx.storage.IModelStore.IdType;

import junit.framework.TestCase;

/**
 * @author gary
 *
 */
public class SpdxListedLicenseWebStoreTest extends TestCase {

	private static final String APACHE_ID = "Apache-2.0";
	private static final String LICENSE_LIST_URI = "https://spdx.org/licenses/";
	private static final String LICENSE_LIST_VERSION = "3.7";
	private static final String APACHE_LICENSE_NAME = "Apache License 2.0";

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link org.spdx.storage.listedlicense.SpdxListedLicenseModelStore#exists(java.lang.String, java.lang.String)}.
	 * @throws InvalidSPDXAnalysisException 
	 */
	public void testExists() throws InvalidSPDXAnalysisException {
		SpdxListedLicenseWebStore sllw = new SpdxListedLicenseWebStore();
		assertTrue(sllw.exists(LICENSE_LIST_URI, APACHE_ID));
		assertFalse(sllw.exists(LICENSE_LIST_URI, "Unknown"));
	}

	/**
	 * Test method for {@link org.spdx.storage.listedlicense.SpdxListedLicenseModelStore#create(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	public void testCreate() throws InvalidSPDXAnalysisException {
		SpdxListedLicenseWebStore sllw = new SpdxListedLicenseWebStore();
		String nextId = sllw.getNextId(IdType.ListedLicense, LICENSE_LIST_URI);
		sllw.create(LICENSE_LIST_URI, nextId, SpdxConstants.CLASS_SPDX_LISTED_LICENSE);
		String result = (String)sllw.getValue(LICENSE_LIST_URI, nextId, SpdxConstants.PROP_LICENSE_ID);
		assertEquals(nextId, result);
	}

	/**
	 * Test method for {@link org.spdx.storage.listedlicense.SpdxListedLicenseModelStore#getNextId(org.spdx.storage.IModelStore.IdType, java.lang.String)}.
	 */
	public void testGetNextId() throws InvalidSPDXAnalysisException {
		SpdxListedLicenseWebStore sllw = new SpdxListedLicenseWebStore();
		String nextId = sllw.getNextId(IdType.ListedLicense, LICENSE_LIST_URI);
		String nextNextId = sllw.getNextId(IdType.ListedLicense, LICENSE_LIST_URI);
		assertTrue(nextId.compareTo(nextNextId) < 0);
	}

	/**
	 * Test method for {@link org.spdx.storage.listedlicense.SpdxListedLicenseModelStore#getSpdxListedLicenseIds()}.
	 */
	public void testGetSpdxListedLicenseIds() throws InvalidSPDXAnalysisException {
		SpdxListedLicenseWebStore sllw = new SpdxListedLicenseWebStore();
		List<String> result = sllw.getSpdxListedLicenseIds();
		assertTrue(result.size() >= 373);
		assertTrue(result.contains(APACHE_ID));
	}

	/**
	 * Test method for {@link org.spdx.storage.listedlicense.SpdxListedLicenseModelStore#getLicenseListVersion()}.
	 */
	public void testGetLicenseListVersion() throws InvalidSPDXAnalysisException {
		SpdxListedLicenseWebStore sllw = new SpdxListedLicenseWebStore();
		assertTrue(LICENSE_LIST_VERSION.compareTo(sllw.getLicenseListVersion()) <= 0);
	}
	
	public void testGetValue() throws InvalidSPDXAnalysisException {
		SpdxListedLicenseWebStore sllw = new SpdxListedLicenseWebStore();
		String result = (String)sllw.getValue(LICENSE_LIST_URI, APACHE_ID, SpdxConstants.PROP_NAME);
		assertEquals(APACHE_LICENSE_NAME, result);
	}
	
	public void testSetValue() throws InvalidSPDXAnalysisException {
		SpdxListedLicenseWebStore sllw = new SpdxListedLicenseWebStore();
		String result = (String)sllw.getValue(LICENSE_LIST_URI, APACHE_ID, SpdxConstants.PROP_NAME);
		assertEquals(APACHE_LICENSE_NAME, result);
		String newName = "new name";
		sllw.setPrimitiveValue(LICENSE_LIST_URI, APACHE_ID, SpdxConstants.PROP_NAME, newName);
		result = (String)sllw.getValue(LICENSE_LIST_URI, APACHE_ID, SpdxConstants.PROP_NAME);
		assertEquals(newName, result);
	}

	public void testCreateLicense() throws InvalidSPDXAnalysisException {
		SpdxListedLicenseWebStore sllw = new SpdxListedLicenseWebStore();
		SpdxListedLicense result = (SpdxListedLicense)SpdxModelFactory.createModelObject(sllw, LICENSE_LIST_URI, APACHE_ID, SpdxConstants.CLASS_SPDX_LISTED_LICENSE);
		assertEquals(APACHE_ID, result.getLicenseId());
		assertEquals(APACHE_LICENSE_NAME, result.getName());
		String licenseText = result.getLicenseText();
		assertTrue(licenseText.length() > 100);
		// TODO: Test other fields
		
	}
}