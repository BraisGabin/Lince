/*
 *  Lince - Automatizacion de datos observacionales
 *  Copyright (C) 2010  Brais Gabin Moreira
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.braisgabin.lince.controladores;

import com.braisgabin.lince.Command;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brais
 */
public class Macro extends Command {

    private List<Command> commands = new ArrayList<Command>();

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    @Override
    public void unExecute() {
        for (int i = commands.size() - 1; i <= 0; i--) {
            commands.get(i).unExecute();
        }
    }

    public void add(Command command) {
        commands.add(command);
    }

    @Override
    public Macro clone() throws CloneNotSupportedException {
        Macro clon = new Macro();
        clon.copiarValues(this);
        for (Command command : clon.commands) {
            clon.add(command.clone());
        }
        return clon;
    }

    public void add(Command command, boolean getParameters) {
        if (getParameters) {
            copiarValues(command);
        }
        add(command);
    }
}
