/*
 * This file is part of the Solid TX project.
 *
 * Copyright (c) 2014. sha1(OWNER) = df334a7237f10846a0ca302bd323e35ee1463931
 * --> See LICENSE.txt for more information.
 *
 * @author BinaryBabel OSS (http://code.binbab.org)
 */

package org.binbab.solidtx;

import org.binbab.solidtx.core.network.NetworkManager;
import org.binbab.solidtx.core.object.ObjectManager;
import org.binbab.solidtx.core.router.Router;
import org.binbab.solidtx.core.storage.StorageManager;
import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

public class TxStackTest {

    @Rule
    public EasyMockRule mocks = new EasyMockRule(this);

    @Mock
    private Router router;

    @Mock
    private ObjectManager om;

    @Mock
    private NetworkManager nm;

    @Mock
    private StorageManager sm;

    private TxStack stack;

    @Before
    public void setUp() throws Exception {
        stack = new TxStackStub(router, om, nm, sm);
    }

    @After
    public void tearDown() throws Exception {

    }

}
