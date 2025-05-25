export class User{
    constructor(private id:number, private name:string, private email: string){}

    getData():string{
        return "id: "+this.id+"; Email: "+this.email;
    }
}