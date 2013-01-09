package ui.controls;

import app.common.listener.MenuBarListener.NavigationListener;
import infrastructure.entity.Board;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 5.12.12
 * Time: 10:21
 */
public class Navigation extends InternalPanel {

    private JTable navigation = new JTable() {
        public boolean isCellEditable(int rowIndex, int vColIndex) {
            return false;
        }
    };
    private HashMap<String, Board> boardsMap;
    private List<NavigationListener> listeners;
    private Board selectedBoard;

    public Navigation(String titleName) {
        super(titleName);
        boardsMap = new HashMap<String, Board>();
        listeners = new ArrayList<NavigationListener>();
        initializeUI();
    }

    public void addBoards(List<Board> boards) {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Navigation");

        for (Board board : boards) {
            defaultTableModel.addRow(new String[]{board.getTitle()});
            boardsMap.put(board.getTitle(), board);
        }
        updateModel(defaultTableModel);
    }

    private void initializeUI() {
        navigation.setTableHeader(null);
        navigation.setCellEditor(null);
        navigation.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        navigation.setBackground(new Color(240, 240, 240));
        navigation.setShowVerticalLines(false);
        navigation.setGridColor(new Color(192, 192, 192));
        navigation.setDefaultRenderer(Object.class, new MyColumnTableCellRenderer());
        navigation.setDefaultRenderer(String.class, new BoardTableCellRenderer());
        navigation.setRowHeight(25);
        JScrollPane jsp = new JScrollPane(navigation);
        add(jsp, BorderLayout.CENTER);

        navigation.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Object selectedCell = getDataFromTable(table, table.getSelectedRow(), table.getSelectedColumn());
                Board board = boardsMap.get(selectedCell.toString());
                for (NavigationListener listener : listeners)
                    listener.boardSelectedChanged(board);
            }
        });
    }

    public Object getDataFromTable(JTable table, int row_index, int col_index) {
        return table.getModel().getValueAt(row_index, col_index);
    }

    private void updateModel(DefaultTableModel defaultTableModel) {
        navigation.setModel(defaultTableModel);
        navigation.setRowSelectionInterval(0, 0);
    }

    public void addActionListener(NavigationListener listener) {
        listeners.add(listener);
    }

    public void setSelectedBoard(Board selectedBoard) {
        for (int i = 0; i < navigation.getRowCount(); i++) {
            String currentBoardTitle = (String) getDataFromTable(navigation, i, 0);
            if (currentBoardTitle.equals(selectedBoard.getTitle())) {
                navigation.setRowSelectionInterval(i, i);
                return;
            }
        }
    }

    class BoardTableCellRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            Object valueAt = table.getModel().getValueAt(row, col);
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            String s = "";
            if (valueAt != null) {
                s = valueAt.toString();
            }

            if (s.equalsIgnoreCase("yellow")) {
                component.setForeground(Color.YELLOW);
                component.setBackground(Color.gray);
            } else {
                component.setForeground(Color.black);
                component.setBackground(Color.WHITE);
            }

            return component;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawLine(0, 0, getWidth(), getHeight());
        }
    }

    public class MyColumnTableCellRenderer extends JLabel implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, final int row, int column) {
            this.setFont(new Font("Calibri", Font.BOLD, 13));
            this.setForeground(new Color(37, 37, 37));
            this.setBorder(new EmptyBorder(10, 10, 10, 10));

            if (hasFocus || isSelected) {
                // setBorder(BorderFactory.createLineBorder(new Color(189,192,199)));
                this.setBackground(new Color(201, 205, 215));
            } else {
                this.setBackground(table.getBackground());
                //this.setBorder(null);
            }
            this.setOpaque(true);
            this.setText((String) value);
            return this;
        }
    }
}
