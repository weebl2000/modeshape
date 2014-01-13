/*
 * ModeShape (http://www.modeshape.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.modeshape.jboss.subsystem;

import org.jboss.as.controller.AbstractAddStepHandler;
import org.jboss.dmr.ModelNode;

/**
 * 
 */
public class AddIndexStorage extends AbstractAddStepHandler {

    public static final AddIndexStorage INSTANCE = new AddIndexStorage();

    private AddIndexStorage() {
    }

    @Override
    protected void populateModel( ModelNode operation,
                                  ModelNode model ) {
    }
}
