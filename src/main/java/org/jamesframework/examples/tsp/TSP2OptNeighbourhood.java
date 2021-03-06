/*
 * Copyright 2014 Ghent University, Bayer CropScience.
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

package org.jamesframework.examples.tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.jamesframework.core.search.neigh.Neighbourhood;

/**
 * Basic 2-opt neighbourhood for the TSP problem.
 * 
 * @author <a href="mailto:herman.debeukelaer@ugent.be">Herman De Beukelaer</a>
 */
public class TSP2OptNeighbourhood implements Neighbourhood<TSPSolution>{
    
    @Override
    public TSP2OptMove getRandomMove(TSPSolution solution, Random rnd) {
        // pick two distinct random positions i,j in the round trip
        int n = solution.getCities().size();
        int i = rnd.nextInt(n);
        int j = rnd.nextInt(n-1);
        if(j >= i){
            j++;
        }
        // return 2-opt TSP move that reverses path from position i to j
        return new TSP2OptMove(i, j);
    }

    @Override
    public List<TSP2OptMove> getAllMoves(TSPSolution solution) {
        // generate a 2-opt TSP move for every pair of positions i,j
        int n = solution.getCities().size();
        List<TSP2OptMove> moves = new ArrayList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i != j){
                    moves.add(new TSP2OptMove(i, j));
                }
            }
        }
        return moves;
    }

}
