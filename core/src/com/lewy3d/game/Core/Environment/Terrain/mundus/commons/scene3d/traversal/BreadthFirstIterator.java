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

import com.badlogic.gdx.utils.Queue;
import com.lewy3d.game.Core.Environment.Terrain.mundus.commons.scene3d.GameObject;

import java.util.Iterator;

/**
 * @author Marcus Brummer
 * @version 21-01-2016
 */
public class BreadthFirstIterator implements Iterator<GameObject> {

    private Queue<GameObject> queue;

    public BreadthFirstIterator(GameObject root) {
        queue = new Queue<GameObject>();
        queue.addLast(root);
    }

    @Override
    public boolean hasNext() {
        return queue.size > 0;
    }

    @Override
    public GameObject next() {
        GameObject first = queue.removeFirst();
        if (first.getChildren() != null) {
            for (GameObject go : first.getChildren()) {
                queue.addLast(go);
            }
        }

        return first;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
