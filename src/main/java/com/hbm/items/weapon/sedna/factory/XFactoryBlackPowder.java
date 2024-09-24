package com.hbm.items.weapon.sedna.factory;

import java.util.function.BiFunction;

import com.hbm.items.ModItems;
import com.hbm.items.weapon.sedna.BulletConfig;
import com.hbm.items.weapon.sedna.Crosshair;
import com.hbm.items.weapon.sedna.GunConfig;
import com.hbm.items.weapon.sedna.ItemGunBaseNT;
import com.hbm.items.weapon.sedna.Receiver;
import com.hbm.items.weapon.sedna.factory.GunFactory.EnumAmmo;
import com.hbm.items.weapon.sedna.mags.MagazineFullReload;
import com.hbm.lib.RefStrings;
import com.hbm.render.anim.BusAnimation;
import com.hbm.render.anim.BusAnimationSequence;
import com.hbm.render.anim.BusAnimationKeyframe.IType;
import com.hbm.render.anim.HbmAnimations.AnimType;

import net.minecraft.item.ItemStack;

public class XFactoryBlackPowder {

	public static void init() {

		BulletConfig stone = new BulletConfig().setItem(EnumAmmo.STONE).setSpread(0.025F).setRicochetAngle(15);
		BulletConfig flint = new BulletConfig().setItem(EnumAmmo.STONE_AP).setSpread(0.01F).setRicochetAngle(5);
		BulletConfig shot = new BulletConfig().setItem(EnumAmmo.STONE_SHOT).setSpread(0.1F).setRicochetAngle(45).setProjectiles(6, 6).setDamage(0.2F);
		
		ModItems.gun_pepperbox = new ItemGunBaseNT(new GunConfig()
				.dura(300).draw(4).inspect(23).crosshair(Crosshair.L_CLASSIC).hud(Lego.HUD_COMPONENT_DURABILITY, Lego.HUD_COMPONENT_AMMO).smoke(true).orchestra(Orchestras.ORCHESTRA_PEPPERBOX)
				.rec(new Receiver(0)
						.dmg(5F).delay(27).reload(67).sound("hbm:weapon.fire.blackPowder", 1.0F, 1.0F)
						.mag(new MagazineFullReload(0, 6).addConfigs(stone, flint, shot))
						.canFire(Lego.LAMBDA_STANDARD_CAN_FIRE).fire(Lego.LAMBDA_STANDARD_FIRE))
				.pp(Lego.LAMBDA_STANDARD_CLICK_PRIMARY) .pr(Lego.LAMBDA_STANDARD_RELOAD) .pt(Lego.LAMBDA_TOGGLE_AIM)
				.decider(GunStateDecider.LAMBDA_STANDARD_DECIDER)
				.anim(LAMBDA_PEPPERBOX_ANIMS)
				).setUnlocalizedName("gun_pepperbox").setTextureName(RefStrings.MODID + ":gun_darter");
	}

	@SuppressWarnings("incomplete-switch") public static BiFunction<ItemStack, AnimType, BusAnimation> LAMBDA_PEPPERBOX_ANIMS = (stack, type) -> {
		switch(type) {
		case CYCLE: return new BusAnimation()
				.addBus("ROTATE", new BusAnimationSequence().addPos(0, 0, 0, 1025).addPos(60, 0, 0, 250))
				.addBus("RECOIL", new BusAnimationSequence().addPos(0, 0, 0, 50).addPos(45, 0, 0, 150, IType.SIN_DOWN).addPos(45, 0, 0, 50).addPos(0, 0, 0, 500, IType.SIN_FULL))
				.addBus("HAMMER", new BusAnimationSequence().addPos(80, 0, 0, 25).addPos(80, 0, 0, 1000).addPos(0, 0, 0, 250))
				.addBus("TRIGGER", new BusAnimationSequence().addPos(1, 0, 0, 25).addPos(1, 0, 0, 250).addPos(0, 0, 0, 100));
		case CYCLE_DRY: return new BusAnimation()
				.addBus("ROTATE", new BusAnimationSequence().addPos(0, 0, 0, 525).addPos(60, 0, 0, 250))
				.addBus("HAMMER", new BusAnimationSequence().addPos(80, 0, 0, 25).addPos(80, 0, 0, 500).addPos(0, 0, 0, 250))
				.addBus("TRIGGER", new BusAnimationSequence().addPos(1, 0, 0, 25).addPos(1, 0, 0, 250).addPos(0, 0, 0, 100));
		case EQUIP: return new BusAnimation()
				.addBus("RECOIL", new BusAnimationSequence().addPos(-45, 0, 0, 0).addPos(0, 0, 0, 200, IType.SIN_DOWN));
		case RELOAD: return new BusAnimation()
				.addBus("RECOIL", new BusAnimationSequence().addPos(90, 0, 0, 500, IType.SIN_FULL).addPos(90, 0, 0, 1600).addPos(0, 0, 0, 500, IType.SIN_FULL).addPos(-5, 0, 0, 200, IType.SIN_UP).addPos(0, 0, 0, 200, IType.SIN_DOWN))
				.addBus("TRANSLATE", new BusAnimationSequence().addPos(0, -12, 5, 500, IType.SIN_FULL).addPos(0, -12, 5, 700).addPos(0, -13, 5, 200).addPos(0, -12, 5, 200).addPos(0, -12, 5, 500).addPos(0, 0, 0, 500, IType.SIN_FULL))
				.addBus("LOADER", new BusAnimationSequence().addPos(0, 0, 0, 500).addPos(0, 5, -5, 0).addPos(0, 0, -0.1, 500, IType.SIN_FULL).addPos(0, 0, -1, 200).addPos(0, 0, -1, 200).addPos(0, 0, -0.1, 200).addPos(0, 5, -5, 500, IType.SIN_FULL).addPos(0, 0, 0, 0))
				.addBus("ROTATE", new BusAnimationSequence().addPos(0, 0, 0, 2600).addPos(-360 * 1, 0, 0, 750, IType.SIN_FULL))
				.addBus("SHOT", new BusAnimationSequence().addPos(1, 0, 0, 1400).addPos(0, 0, 0, 0));
		case INSPECT: return new BusAnimation()
				.addBus("ROTATE", new BusAnimationSequence().addPos(-360 * 1, 0, 0, 750, IType.SIN_FULL))
				.addBus("RECOIL", new BusAnimationSequence().addPos(-5, 0, 0, 200, IType.SIN_UP).addPos(0, 0, 0, 200, IType.SIN_DOWN));
		case JAMMED: break;
		}
		
		return null;
	};
}
