import { SharedModule } from "./../shared/shared.module";
import { NotificationService } from "./../shared/notification.service";
import { DashboardService } from "./dashboard.service";
import { NgModule } from "@angular/core";

import { DashboardComponent } from "./dashboard.component";
import { DashboardRoutingModule } from "./dashboard-routing.module";
import { TabsModule } from "ngx-bootstrap/tabs";

@NgModule({
  imports: [DashboardRoutingModule, TabsModule.forRoot(), SharedModule],
  declarations: [DashboardComponent],
  providers: [DashboardService, NotificationService]
})
export class DashboardModule {}
