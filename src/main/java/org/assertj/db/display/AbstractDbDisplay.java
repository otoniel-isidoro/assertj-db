/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.display;

import org.assertj.db.display.impl.RepresentationType;
import org.assertj.db.navigation.Position;
import org.assertj.db.navigation.PositionWithColumns;
import org.assertj.db.navigation.origin.OriginWithColumnsAndRows;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Column;
import org.assertj.db.type.Row;

import java.util.List;

import static org.assertj.db.util.Descriptions.getColumnDescription;
import static org.assertj.db.util.Descriptions.getRowDescription;

/**
 * Base class for all data ({@code Table} or {@code Request}) displays.
 *
 * @author Régis Pouiller
 *
 * @param <D> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <A> The class of the original assert (an sub-class of {@link AbstractDbDisplay}).
 * @param <C> The class of this assert (an sub-class of {@link AbstractColumnDisplay}).
 * @param <CV> The class of this assertion on the value (an sub-class of {@link AbstractColumnValueDisplay}).
 * @param <R> The class of the equivalent row assert (an sub-class of {@link AbstractRowDisplay}).
 * @param <RV> The class of the equivalent row assertion on the value (an sub-class of {@link AbstractRowValueDisplay}).
 */
public abstract class AbstractDbDisplay<D extends AbstractDbData<D>, A extends AbstractDbDisplay<D, A, C, CV, R, RV>, C extends AbstractColumnDisplay<D, A, C, CV, R, RV>, CV extends AbstractColumnValueDisplay<D, A, C, CV, R, RV>, R extends AbstractRowDisplay<D, A, C, CV, R, RV>, RV extends AbstractRowValueDisplay<D, A, C, CV, R, RV>>
        extends AbstractDisplay<A>
        implements OriginWithColumnsAndRows<C, R> {

  /**
   * The actual value on which the assertion is.
   */
  protected final D actual;

  /**
   * Position of navigation to column.
   */
  private final PositionWithColumns<A, C, Column> columnPosition;
  /**
   * Position of navigation to row.
   */
  private final Position<A, R, Row> rowPosition;

  /**
   * Constructor of the database assertions.
   *
   * @param actualValue The actual value on which the assertion is.
   * @param selfType Type of this assertion class.
   * @param columnAssertType Class of the assertion on the column.
   * @param rowAssertType Class of the assertion on the row.
   */
  AbstractDbDisplay(D actualValue, Class<A> selfType, Class<C> columnAssertType, Class<R> rowAssertType) {
    super(selfType);
    actual = actualValue;
    columnPosition = new PositionWithColumns(selfType.cast(this), columnAssertType) {
      @Override protected String getDescription(int index) {
        List<String> columnsNameList = actual.getColumnsNameList();
        String columnName = columnsNameList.get(index);
        return getColumnDescription(myself.getInfo(), index, columnName);
      }
    };
    rowPosition = new Position(selfType.cast(this), rowAssertType) {
      @Override protected String getDescription(int index) {
        return getRowDescription(myself.getInfo(), index);
      }
    };
    withType(RepresentationType.PLAIN);
  }

  /** {@inheritDoc} */
  @Override
  public R row() {
    return rowPosition.getInstance(actual.getRowsList()).withType(displayType);
  }

  /** {@inheritDoc} */
  @Override
  public R row(int index) {
    return rowPosition.getInstance(actual.getRowsList(), index).withType(displayType);
  }

  /** {@inheritDoc} */
  @Override
  public C column() {
    return columnPosition.getInstance(actual.getColumnsList()).withType(displayType);
  }

  /** {@inheritDoc} */
  @Override
  public C column(int index) {
    return columnPosition.getInstance(actual.getColumnsList(), index).withType(displayType);
  }

  /** {@inheritDoc} */
  @Override
  public C column(String columnName) {
    return columnPosition.getInstance(actual.getColumnsList(), actual.getColumnsNameList(), columnName).withType(displayType);
  }
}
