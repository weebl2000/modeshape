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
package org.modeshape.connector.git;

import org.modeshape.common.annotation.Immutable;

/**
 * 
 */
@Immutable
public final class CallSpecification {

    protected static final String DELIMITER_STR = "/";
    protected static final char DELIMITER = DELIMITER_STR.charAt(0);

    private final String id;
    private final String[] parts;
    private final int numParts;
    private final int parameterCount;

    public CallSpecification( String id ) {
        this.id = id;
        String relative = id.replaceFirst("^[/]", "");
        this.parts = relative.split("/");
        this.numParts = this.parts.length;
        this.parameterCount = numParts - 1;
        assert this.numParts > 0;
    }

    public String getFunctionName() {
        return this.parts[0];
    }

    /**
     * The identifier of this call.
     * 
     * @return the identifier; never null
     */
    public String getId() {
        return id;
    }

    public int parameterCount() {
        return parameterCount;
    }

    /**
     * Get the specified parameter.
     * 
     * @param index the 0-based index of the parameter, excluding the first segment that is the name
     * @return the parameter
     */
    public String parameter( int index ) {
        return parts[index + 1];
    }

    public String parametersAsPath( int fromIndex ) {
        return parametersAsPath(fromIndex, numParts);
    }

    public String parametersAsPath( int fromIndex,
                                    int toIndex ) {
        ++fromIndex; // ignore the name
        assert fromIndex < numParts;
        assert toIndex <= numParts;
        StringBuilder sb = new StringBuilder();
        for (int i = fromIndex; i != toIndex; ++i) {
            sb.append(DELIMITER);
            sb.append(parts[i]);
        }
        return sb.toString();
    }

    public String lastParameter() {
        if (numParts == 0) return null;
        return this.parts[numParts - 1];
    }

    public String childId( String childPart ) {
        return id + DELIMITER + childPart;
    }

    public String getParentId() {
        if (numParts == 0) {
            // There is no parent ...
            return "";
        }
        if (numParts == 1) {
            // The parent is the root ...
            return GitRoot.ID;
        }
        StringBuilder sb = new StringBuilder();
        int length = numParts - 1;
        for (int i = 0; i != length; ++i) {
            String part = parts[i];
            sb.append(DELIMITER);
            sb.append(part);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return id;
    }
}
