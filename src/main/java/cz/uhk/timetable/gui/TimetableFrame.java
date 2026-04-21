package cz.uhk.timetable.gui;

import cz.uhk.timetable.model.LocationTimetable;
import cz.uhk.timetable.utils.TimetableProvider;
import cz.uhk.timetable.utils.impl.MockTimetableProvider;
import cz.uhk.timetable.utils.impl.StagTimetableProvider;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TimetableFrame extends JFrame {
        private LocationTimetable timetable;
        private TimetableProvider provider = new StagTimetableProvider();
        private JTable tabTimetable;
        private JPanel panTimetable;

        private String[] building = new String[]{"J", "A", "S"};
        private String[] room = new String[]{"J1", "J2", "J3"};

        public TimetableFrame(){
            super("FIM Rozvrhy");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            initGui();
        }
        private void initGui(){
            timetable = new LocationTimetable();

            panTimetable = new JPanel();

            JComboBox<String> comboBuilding = new JComboBox<>(building);
            JComboBox<String> comboRoom = new JComboBox<>(room);
            JButton btnSubmit = new JButton("Hledat");

            panTimetable.add(new JLabel("Budova"));
            panTimetable.add(comboBuilding);
            panTimetable.add(new JLabel("Místnost"));
            panTimetable.add(comboRoom);
            panTimetable.add(btnSubmit);

            add(panTimetable, BorderLayout.NORTH);

            TimetableModel model = new TimetableModel();
            tabTimetable = new JTable(model);
            tabTimetable.setAutoCreateRowSorter(true);
            add(new JScrollPane(tabTimetable), BorderLayout.CENTER);


            btnSubmit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    timetable = provider.read(comboBuilding.getSelectedItem().toString(), comboRoom.getSelectedItem().toString());
                    model.fireTableDataChanged();
                }
            });

            pack();
        }




        class TimetableModel extends AbstractTableModel{

            @Override
            public String getColumnName(int column) {
                switch (column){

                    case 0: return "Zkratka";
                    case 1: return "Název";
                    case 2: return "Den";
                    case 3: return "Začátek";
                    case 4: return "Konec";
                    case 5: return "Učitel";

                }
                return super.getColumnName(column);
            }

            @Override
            public int getRowCount() {
                return timetable.getActivities().size();
            }

            @Override
            public int getColumnCount() {
                return 6;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {

                var act = timetable.getActivities().get(rowIndex);

                switch (columnIndex){

                    case 0: return act.getCode();
                    case 1: return act.getName();
                    case 2: return act.getDay();
                    case 3: return act.getStartTime();
                    case 4: return act.getEndTime();
                    case 5: return act.getTeacher();

                }

                return null;
            }
        }
}
