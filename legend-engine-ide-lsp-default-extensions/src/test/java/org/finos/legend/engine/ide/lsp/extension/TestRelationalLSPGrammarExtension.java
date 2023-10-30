// Copyright 2023 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.engine.ide.lsp.extension;

import org.finos.legend.engine.ide.lsp.extension.declaration.LegendDeclaration;
import org.finos.legend.pure.m2.relational.M2RelationalPaths;
import org.junit.jupiter.api.Test;

public class TestRelationalLSPGrammarExtension extends AbstractLSPGrammarExtensionTest
{
    @Test
    public void testGetName()
    {
        testGetName("Relational");
    }

    @Test
    public void testGetDeclarations()
    {
        testGetDeclarations("###Relational\n" +
                        "\r\n" +
                        "\n" +
                        "Database test::store::TestDatabase\n" +
                        "(\r\n" +
                        "    Table T1\n" +
                        "    (\n" +
                        "        ID INT, NAME VARCHAR(200)\n" +
                        "    )\n" +
                        "\n" +
                        "    Schema S1\n" +
                        "    (\n" +
                        "        Table T2\n" +
                        "        (\n" +
                        "            ID INT,\n" +
                        "            NAME VARCHAR(200)\n" +
                        "        )\n" +
                        "    )\n" +
                        ")\n",
                LegendDeclaration.builder().withIdentifier("test::store::TestDatabase").withClassifier(M2RelationalPaths.Database).withLocation(3, 0, 18, 0)
                        .withChild(LegendDeclaration.builder().withIdentifier("S1").withClassifier(M2RelationalPaths.Schema).withLocation(10, 4, 17, 4)
                                .withChild(LegendDeclaration.builder().withIdentifier("T2").withClassifier(M2RelationalPaths.Table).withLocation(12, 8, 16, 8)
                                        .withChild(LegendDeclaration.builder().withIdentifier("ID").withClassifier(M2RelationalPaths.Column).withLocation(14, 12, 14, 17).build())
                                        .withChild(LegendDeclaration.builder().withIdentifier("NAME").withClassifier(M2RelationalPaths.Column).withLocation(15, 12, 15, 28).build())
                                        .build())
                                .build())
                        .withChild(LegendDeclaration.builder().withIdentifier("default").withClassifier(M2RelationalPaths.Schema).withLocation(3, 0, 18, 0)
                                .withChild(LegendDeclaration.builder().withIdentifier("T1").withClassifier(M2RelationalPaths.Table).withLocation(5, 4, 8, 4)
                                        .withChild(LegendDeclaration.builder().withIdentifier("ID").withClassifier(M2RelationalPaths.Column).withLocation(7, 8, 7, 13).build())
                                        .withChild(LegendDeclaration.builder().withIdentifier("NAME").withClassifier(M2RelationalPaths.Column).withLocation(7, 16, 7, 32).build())
                                        .build())
                                .build())
                        .build()
        );
    }

    @Override
    protected LegendLSPGrammarExtension newExtension()
    {
        return new RelationalLSPGrammarExtension();
    }
}