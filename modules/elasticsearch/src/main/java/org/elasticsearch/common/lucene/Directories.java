/*
 * Licensed to Elastic Search and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Elastic Search licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.common.lucene;

import org.apache.lucene.store.Directory;
import org.elasticsearch.common.unit.ByteSizeValue;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A set of utilities for Lucene {@link Directory}.
 *
 * @author kimchy (shay.banon)
 */
public class Directories {

    /**
     * Returns the estimated size of a {@link Directory}.
     */
    public static ByteSizeValue estimateSize(Directory directory) throws IOException {
        long estimatedSize = 0;
        String[] files = directory.listAll();
        for (String file : files) {
            try {
                estimatedSize += directory.fileLength(file);
            } catch (FileNotFoundException e) {
                // ignore, the file is not there no more
            }
        }
        return new ByteSizeValue(estimatedSize);
    }

    private Directories() {

    }
}
