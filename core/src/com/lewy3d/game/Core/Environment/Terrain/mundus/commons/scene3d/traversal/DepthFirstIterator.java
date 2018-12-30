/*
 * Copyright (c) 2016. See AUTHORS file.
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

package com.lewy3d.game.Core.Environment.Terrain.mundus.commons.scene3d.traversal;

import com.lewy3d.game.Core.Environment.Terrain.mundus.commons.scene3d.GameObject;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author Marcus Brummer
 * @version 20-01-2016
 */
public class DepthFirstIterator implements Iterator<GameObject> {

    private Stack<GameObject> stack;

    public DepthFirstIterator(GameObject root) {
        stack = new Stack<GameObject>();
        stack.push(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public GameObject next() {
        GameObject top = stack.pop();
        if (top.getChildren() != null) {
            for (GameObject child : top.getChildren()) {
                stack.push(child);
            }
        }

        return top;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
