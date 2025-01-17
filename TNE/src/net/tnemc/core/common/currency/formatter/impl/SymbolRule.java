package net.tnemc.core.common.currency.formatter.impl;

import net.tnemc.core.common.currency.TNECurrency;
import net.tnemc.core.common.currency.formatter.FormatRule;
import org.bukkit.Location;

import java.math.BigDecimal;

/**
 * The New Economy Minecraft Server Plugin
 * <p>
 * Created by creatorfromhell on 6/10/2019.
 * <p>
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/ or send a letter to
 * Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
 * Created by creatorfromhell on 06/30/2017.
 */
public class SymbolRule implements FormatRule {
  @Override
  public String name() {
    return "symbol";
  }

  @Override
  public String format(TNECurrency currency, BigDecimal amount, Location location, String player, String formatted) {
    return formatted.replace("<symbol>", currency.symbol());
  }
}